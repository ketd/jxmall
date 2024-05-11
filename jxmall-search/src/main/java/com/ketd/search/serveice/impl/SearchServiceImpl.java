package com.ketd.search.serveice.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.FieldValue;

import co.elastic.clients.elasticsearch._types.SortOrder;
import co.elastic.clients.elasticsearch._types.aggregations.*;
import co.elastic.clients.elasticsearch._types.query_dsl.*;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import co.elastic.clients.elasticsearch.core.search.HitsMetadata;
import co.elastic.clients.json.JsonData;
import co.elastic.clients.transport.ElasticsearchTransport;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ketd.common.domain.search.SkuEsModel;
import com.ketd.common.result.Result;
import com.ketd.search.constant.EsConstant;
import com.ketd.search.serveice.SearchService;
import com.ketd.search.vo.SearchParam;
import com.ketd.search.vo.SearchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import static com.ketd.search.constant.EsConstant.JXMALL_PRODUCT_INDEX;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.serveice.impl
 * @Author: ketd
 * @CreateTime: 2024-05-09  13:17
 */
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private ElasticsearchClient restClient;

    @Autowired
    private ElasticsearchTransport transport;

    @Autowired
    private ObjectMapper objectMapper;


    @Override
    public Result<?> search(SearchParam searchParam) throws IOException {
        try {
            Map<String, Aggregation> aggregationsMap = new HashMap<>();
            aggregationsMap.put("brand_agg", Aggregation.of(a -> a
                    .terms(t -> t.field("brandId").size(10))
                    .aggregations("brand_name_agg", aa -> aa
                            .terms(tt -> tt.field("brandName").size(10))
                    )
                    .aggregations("brand_img_agg", aa -> aa
                            .terms(tt -> tt.field("brandImg").size(10))
                    )
            ));
            aggregationsMap.put("catalog_agg", Aggregation.of(a -> a
                    .terms(t -> t.field("catalogId").size(10))
                    .aggregations("catalog_name_agg", aa -> aa
                            .terms(tt -> tt.field("catalogName").size(10))
                    )
            ));
            aggregationsMap.put("attrs_agg", Aggregation.of(a -> a
                    .nested(n -> n.path("attrs"))
                    .aggregations("attr_id_agg", aa -> aa
                            .terms(tt -> tt.field("attrs.attrId").size(10))
                            .aggregations("attr_name_agg", aaa -> aaa
                                    .terms(ttt -> ttt.field("attrs.attrName").size(10))
                            )
                            .aggregations("attr_value_agg", aaa -> aaa
                                    .terms(ttt -> ttt.field("attrs.attrValue").size(10))
                            )
                    )
            ));
            final SearchResponse<Object> searchResponse = restClient.search(s -> s
                            .index(JXMALL_PRODUCT_INDEX)
                            .query(q -> q
                                    .bool(b -> b
                                            .must(m -> {
                                                        List<Query> musts = new ArrayList<>();
                                                        // 添加skuTitle过滤条件
                                                        if (searchParam.getKeyword() != null) {
                                                            musts.add(Query.of(qf -> qf
                                                                    .match(mq -> mq
                                                                            .field("skuTitle")
                                                                            .query(searchParam.getKeyword())
                                                                    )
                                                            ));
                                                        }
                                                        return m.bool(bb -> bb.must(musts));
                                                    }
                                            )
                                            .filter(f -> {
                                                List<Query> filters = new ArrayList<>();

                                                // 添加catalogId过滤条件
                                                if (searchParam.getCatalogId() != null) {
                                                    filters.add(Query.of(qf -> qf
                                                            .term(t -> t
                                                                    .field("catalogId")
                                                                    .value(FieldValue.of(searchParam.getCatalogId().toString())) // 假定catalogId为字符串类型
                                                            )
                                                    ));
                                                }

                                                // 添加brandId过滤条件
                                                if (searchParam.getBrandId() != null && !searchParam.getBrandId().isEmpty()) {
                                                    filters.add(Query.of(qf -> qf
                                                            .terms(t -> t
                                                                    .field("brandId")
                                                                    .terms(terms -> terms
                                                                            .value(searchParam.getBrandId().stream()
                                                                                    .map(Object::toString)
                                                                                    .map(FieldValue::of)
                                                                                    .collect(Collectors.toList())
                                                                            )
                                                                    )
                                                            )
                                                    ));
                                                }

                                                if (searchParam.getAttrs() != null && !searchParam.getAttrs().isEmpty()) {
                                                    searchParam.getAttrs().forEach(attr -> {
                                                        filters.add(Query.of(qf -> qf
                                                                .nested(n -> n
                                                                        .path("attrs")
                                                                        .query(nq -> nq
                                                                                .bool(nb -> nb
                                                                                        .must(nm -> nm
                                                                                                .term(t -> t
                                                                                                        .field("attrs.attrId")
                                                                                                        .value(attr.getAttrId())
                                                                                                )
                                                                                        )
                                                                                        .must(nm -> nm
                                                                                                .terms(t -> t
                                                                                                        .field("attrs.attrValue")
                                                                                                        .terms(terms -> terms.value(attr.getAttrNames().stream()
                                                                                                                .map(FieldValue::of)
                                                                                                                .collect(Collectors.toList()))
                                                                                                        )
                                                                                                )
                                                                                        )
                                                                                )
                                                                        )
                                                                )
                                                        ));
                                                    });
                                                }


                                                // 添加hasStock过滤条件
                                                if (searchParam.getHasStock() != null) {
                                                    filters.add(Query.of(qf -> qf
                                                            .term(t -> t
                                                                    .field("hasStock")
                                                                    .value(searchParam.getHasStock().equals(1)) // 假定hasStock为Boolean类型
                                                            )
                                                    ));
                                                }


                                                // 添加价格范围过滤skuPrice 0_500 _200 3000_ 价格区间
                                                if (StringUtils.hasLength(searchParam.getSkuPrice())) {
                                                    String[] priceRange = searchParam.getSkuPrice().split("(?<=\\d)(?=_)|(?<=_)(?=\\d)");

                                                    System.out.println(priceRange);
                                                    if (priceRange.length == 3) {
                                                        filters.add(Query.of(qf -> qf
                                                                .range(r -> r
                                                                        .field("skuPrice")
                                                                        .gte(JsonData.of(priceRange[0])) // 价格为字符串类型
                                                                        .lte(JsonData.of(priceRange[2]))
                                                                )
                                                        ));
                                                    } else if (priceRange.length == 2) {
                                                        if (Objects.equals(priceRange[0], "_")) {
                                                            filters.add(Query.of(qf -> qf
                                                                    .range(r -> r
                                                                            .field("skuPrice")
                                                                            .lte(JsonData.of(priceRange[1]))
                                                                    )
                                                            ));
                                                        } else {
                                                            filters.add(Query.of(qf -> qf
                                                                    .range(r -> r
                                                                            .field("skuPrice")
                                                                            .gte(JsonData.of(priceRange[0]))
                                                                    )
                                                            ));
                                                        }
                                                    }
                                                }

                                                return f.bool(bb -> bb.filter(filters));
                                            })
                                    )

                            )
                            .aggregations(aggregationsMap)
                            // 排序skuPrice_asc/skuPrice_desc
                            .sort(so -> {
                                if (searchParam.getSort()!= null) {
                                    String[] sort = searchParam.getSort().split("_");
                                    if (sort.length == 2) {
                                        String sortField = sort[0];
                                        SortOrder sortOrder = "asc".equals(sort[1]) ? SortOrder.Asc : SortOrder.Desc;
                                        return so.field(f -> f.field(sortField).order(sortOrder));
                                    }
                                }
                                return  so.score(f -> f.order(SortOrder.Desc)); //如果需要，添加默认排序条件或仅返回“so”
                            })
                            .highlight(h -> h
                                    .fields(
                                            "skuTitle", fh -> {
                                                if (searchParam.getKeyword() != null) {
                                                    fh.preTags("<b style='color:red'>").postTags("</b>");
                                                }
                                                return fh;
                                            }
                                    )
                            )
                            .from((searchParam.getPageNum() - 1) * searchParam.getPageSize())
                            .size(searchParam.getPageSize())
                    , Object.class);


            // 返回结果
            return Result.ok(buildSearchResult(searchResponse, searchParam));

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            transport.close();
        }

    }

    private SearchResult buildSearchResult(SearchResponse<Object> searchResponse, SearchParam searchParam) {
        SearchResult searchResult = new SearchResult();


        HitsMetadata<Object> hits = searchResponse.hits();

        List<SkuEsModel> skuEsModelList = getSkuEsModels(hits,searchParam);
        List<SearchResult.BrandVO> brands = new ArrayList<>();

        Map<String, Aggregate> aggregationsMap = searchResponse.aggregations();
        List<LongTermsBucket> buckets = aggregationsMap.get("brand_agg").lterms().buckets().array();
        buckets.forEach(bucket -> {
            SearchResult.BrandVO brandVO = new SearchResult.BrandVO();
            brandVO.setBrandId(bucket.key());
            String brand_name_agg = bucket.aggregations().get("brand_name_agg").sterms().buckets().array().get(0).key().stringValue();
            brandVO.setBrandName(String.valueOf(brand_name_agg));
            String brand_img_agg = bucket.aggregations().get("brand_img_agg").sterms().buckets().array().get(0).key().stringValue();
            brandVO.setImgUrl(String.valueOf(brand_img_agg));
            brands.add(brandVO);
        });


        List<LongTermsBucket> catalogBuckets = aggregationsMap.get("catalog_agg").lterms().buckets().array();
        List<SearchResult.CatalogIdVO> catalogs = new ArrayList<>();
        catalogBuckets.forEach(bucket -> {
            SearchResult.CatalogIdVO catalogVO = new SearchResult.CatalogIdVO();
            catalogVO.setCatalogId(bucket.key());
            String catalog_name_agg = bucket.aggregations().get("catalog_name_agg").sterms().buckets().array().get(0).key().stringValue();
            catalogVO.setCatalogName(String.valueOf(catalog_name_agg));
            catalogs.add(catalogVO);
        });



        List<LongTermsBucket> attrBuckets = aggregationsMap.get("attrs_agg").nested().aggregations().get("attr_id_agg").lterms().buckets().array();
        List<SearchResult.AttrVo> attrs = new ArrayList<>();
        attrBuckets.forEach(bucket -> {
            SearchResult.AttrVo attrVO = new SearchResult.AttrVo();
            attrVO.setAttrId(bucket.key());
            String attr_name_agg = bucket.aggregations().get("attr_name_agg").sterms().buckets().array().get(0).key().stringValue();
            attrVO.setAttrName(String.valueOf(attr_name_agg));
            String attrValues = bucket.aggregations().get("attr_value_agg").sterms().buckets().array().stream().map(StringTermsAggregate->{
               return StringTermsAggregate.key().stringValue();
            }).map(Object::toString).collect(Collectors.joining(","));
           /* String attrValues = bucket.aggregations().get("attr_value_agg").lterms().buckets().array().stream().map(LongTermsBucket::key).map(Object::toString).collect(Collectors.joining(","));*/
            attrVO.setAttrValue(attrValues);
            attrs.add(attrVO);
        });


        //
        searchResult.setAttrs(attrs);
        searchResult.setCatalogs(catalogs);
        searchResult.setProducts(skuEsModelList);
        searchResult.setBrands(brands);
        searchResult.setPageNum(searchParam.getPageNum());
        long total = 0L;
        if (hits.total() != null) {
            total = hits.total().value();
        }
        searchResult.setTotal(total);

        long totalPages = total % EsConstant.PRODUCT_PAGESIZE == 0 ? total / EsConstant.PRODUCT_PAGESIZE : total / EsConstant.PRODUCT_PAGESIZE + 1;

        searchResult.setTotalPages(totalPages);
        return searchResult;
    }

    private List<SkuEsModel> getSkuEsModels(HitsMetadata<Object> hits,SearchParam searchParam) {
        List<Hit<Object>> hitList = hits.hits();
        List<SkuEsModel> skuEsModelList = new ArrayList<>();

        if (hitList != null && !hitList.isEmpty()) {
            hitList.forEach(hit -> {
                System.out.println(hit);
                String source;
                try {
                    source = objectMapper.writeValueAsString(hit.source());
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                SkuEsModel skuEsModel;
                try {
                    skuEsModel = objectMapper.readValue(source, SkuEsModel.class);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                if (searchParam.getKeyword() != null) {
                    skuEsModel.setSkuTitle(hit.highlight().get("skuTitle").get(0));
                }
                skuEsModelList.add(skuEsModel);

            });
        }
        return skuEsModelList;
    }

}
