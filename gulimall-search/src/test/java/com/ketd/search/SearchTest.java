package com.ketd.search;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchAllQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.MatchQuery;
import co.elastic.clients.elasticsearch._types.query_dsl.Query;
import co.elastic.clients.elasticsearch.core.*;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.bulk.CreateOperation;
import co.elastic.clients.elasticsearch.indices.*;
import co.elastic.clients.elasticsearch.indices.ExistsRequest;
import co.elastic.clients.transport.ElasticsearchTransport;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @BelongsProject: gulimall
 * @BelongsPackage: com.ketd.search
 * @Author: ketd
 * @CreateTime: 2024-04-09  19:34
 */
@SpringBootTest
public class SearchTest {

    @Autowired
    private ElasticsearchClient setRestClient;

    @Autowired
    private ElasticsearchTransport transport;

    @Test
    void insert() throws IOException {
        final ElasticsearchIndicesClient indices = setRestClient.indices();

        ExistsRequest existsRequest = new ExistsRequest.Builder().index("test").build();
        final boolean isExistence = indices.exists(existsRequest).value();
        if (isExistence) {
            System.out.println("索引存在");
        } else {

            CreateIndexRequest request = new CreateIndexRequest.Builder().index("test").build();
            final CreateIndexResponse createIndexRequest = indices.create(request);
            System.out.println(createIndexRequest);

        }


        transport.close();
    }

    @Test
    void searchIndex() throws IOException {
        final ElasticsearchIndicesClient indices = setRestClient.indices();
        GetIndexRequest getIndexRequest = new GetIndexRequest.Builder().index("bank").build();
        final GetIndexResponse getIndexResponse = indices.get(getIndexRequest);
        System.out.println(getIndexResponse);
        transport.close();
    }

    @Test
    void addDoc() throws IOException {

        User user = new User();
        user.setId(1);
        user.setName("张三");
        user.setAge(22);

        CreateRequest<User> createRequest = new CreateRequest.Builder<User>()
                .index("test")
                .id("1")
                .document(user)
                .build();

        final CreateResponse getIndexResponse = setRestClient.create(createRequest);

        System.out.println(getIndexResponse);
        transport.close();
    }

    //批量添加
    @Test
    void batchAdd() throws IOException {

        List<BulkOperation> operations = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            CreateOperation<User> operationObj = new CreateOperation.Builder<User>()
                    .index("test")
                    .id(i + "")
                    .document(new User(i, "张三" + i, 22 + i))
                    .build();
            BulkOperation operation = new BulkOperation.Builder().create(operationObj).build();
            operations.add(operation);

        }

        BulkRequest bulkRequest = new BulkRequest.Builder()
                .operations(operations)
                .build();
        final BulkResponse bulkResponse = setRestClient.bulk(bulkRequest);
        System.out.println(bulkResponse);
        transport.close();
    }


    //文档删除
    @Test
    void deleteDoc() throws IOException {
        DeleteRequest deleteRequest = new DeleteRequest.Builder().index("test").id("1").build();
        final DeleteResponse deleteResponse = setRestClient.delete(deleteRequest);
        System.out.println(deleteResponse);
        transport.close();
    }

    //search查询文档
    @Test
    void searchDoc() throws IOException {
        MatchQuery matchQuery = new MatchQuery.Builder()
                .field("firstname")
                .query("Nanette")
                .build();

        Query query = new Query.Builder()
                .match(matchQuery)
                .build();
        SearchRequest searchRequest = new SearchRequest.Builder()
                .query(query)
                .index("bank")
                .build();
        final SearchResponse<Object> searchResponse = setRestClient.search(searchRequest, Object.class);
        System.out.println(searchResponse);
        transport.close();
    }

    //查询文档使用数据
    @Test
    void searchDoc2() throws IOException {

      /*  MatchQuery matchQuery = new MatchQuery.Builder()
                .query()
                .build();
        Query query = new Query.Builder()
                .match(matchQuery)
                .build();

        SearchRequest searchRequest = new SearchRequest.Builder()
                .query(query)
                .index("bank")
                .build();
        final SearchResponse<Object> searchResponse = setRestClient.search(searchRequest, Object.class);
        System.out.println(searchResponse);
        transport.close();*/
    }

    //分页查询
    @Test
    void searchDoc3() throws IOException {

        MatchAllQuery  matchAllQuery = new MatchAllQuery.Builder().build();

        Query query = new Query.Builder()
                .matchAll(matchAllQuery)
                .build();

        SearchRequest searchRequest = new SearchRequest.Builder()
                .query(query)
                .index("bank")
                .from(0)
                .size(200)
                .build();

        final SearchResponse<Object> searchResponse = setRestClient.search(searchRequest, Object.class);
        System.out.println(searchResponse);
        transport.close();
    }


}
