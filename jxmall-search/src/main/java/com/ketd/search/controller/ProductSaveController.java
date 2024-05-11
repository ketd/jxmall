package com.ketd.search.controller;


import com.ketd.common.domain.search.SkuEsModel;
import com.ketd.common.result.Result;
import com.ketd.search.serveice.SaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Description:
 * @BelongsProject: gulimall
 * @BelongsPackage: com.ketd.controller
 * @Author: ketd
 * @CreateTime: 2024-04-23  12:21
 */
@RequestMapping("/search/save")
@RestController
public class ProductSaveController {

    @Autowired
    private SaveService saveService;
    @PostMapping("/product")
    public Result<?> save(@RequestBody List<SkuEsModel> skuInfoTO){
        return saveService.save(skuInfoTO);
    }
}
