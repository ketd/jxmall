package com.ketd.common.api.search;



import com.ketd.common.domain.search.SkuEsModel;
import com.ketd.common.result.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @Description:
 * @BelongsProject: gulimall
 * @BelongsPackage: com.ketd.common.api.search
 * @Author: ketd
 * @CreateTime: 2024-04-22  21:25
 */
@FeignClient(value = "cloud-search-service")
public interface SearchOpenFeignApi {
    @PostMapping("/search/save/product")
    public Result<?> upProduct(@RequestBody List<SkuEsModel> skuInfoTO);
}
