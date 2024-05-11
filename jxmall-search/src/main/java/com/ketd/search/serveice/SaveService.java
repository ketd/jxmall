package com.ketd.search.serveice;

import com.ketd.common.domain.search.SkuEsModel;
import com.ketd.common.result.Result;

import java.util.List;

/**
 * @Description:
 * @BelongsProject: gulimall
 * @BelongsPackage: com.ketd.serveice.impl
 * @Author: ketd
 * @CreateTime: 2024-04-23  12:23
 */

public interface SaveService {
    Result<?> save(List<SkuEsModel> skuInfoTO);
}
