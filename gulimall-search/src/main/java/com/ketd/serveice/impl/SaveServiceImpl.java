package com.ketd.serveice.impl;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.BulkRequest;
import co.elastic.clients.elasticsearch.core.BulkResponse;
import co.elastic.clients.elasticsearch.core.bulk.BulkOperation;
import co.elastic.clients.elasticsearch.core.bulk.CreateOperation;
import co.elastic.clients.transport.ElasticsearchTransport;
import com.ketd.common.domain.search.SkuEsModel;
import com.ketd.common.result.Result;
import com.ketd.serveice.SaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @BelongsProject: gulimall
 * @BelongsPackage: com.ketd.serveice.impl
 * @Author: ketd
 * @CreateTime: 2024-04-23  12:23
 */
@Service
public class SaveServiceImpl implements SaveService {

    @Autowired
    private ElasticsearchClient setRestClient;

    @Autowired
    private ElasticsearchTransport transport;
    @Override
    public Result<?> save(List<SkuEsModel> skuInfoTO) {
        try {
            List<BulkOperation> operations = new ArrayList<>();
            skuInfoTO.forEach(item -> {
                CreateOperation<SkuEsModel> operationObj = new CreateOperation.Builder<SkuEsModel>()
                        .index("product")
                        .id(item.getSkuId()+"")
                        .document(item)
                        .build();
                BulkOperation operation = new BulkOperation.Builder().create(operationObj).build();
                operations.add(operation);

            });

            BulkRequest bulkRequest = new BulkRequest.Builder()
                    .operations(operations)
                    .build();
            final BulkResponse bulkResponse = setRestClient.bulk(bulkRequest);
            System.out.println(bulkResponse);
            transport.close();
            return Result.ok(null);
        } catch (IOException e) {
            return Result.error(e.getMessage());
        }
    }
}
