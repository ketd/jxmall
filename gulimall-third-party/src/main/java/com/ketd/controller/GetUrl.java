package com.ketd.controller;

import com.ketd.common.result.ResultCodeEnum;
import com.ketd.utils.GetCosStsClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ketd.common.result.Result;

import java.net.URL;

/**
 * @Description:
 * @BelongsProject: gulimall
 * @BelongsPackage: com.ketd.controller
 * @Author: ketd
 * @CreateTime: 2024-04-13  18:25
 */
@Tag(name = "商品属性Controller")
@RestController
@CrossOrigin
@RequestMapping(value = "gulimall-third-party/getUrl")
public class GetUrl {

    @Autowired
    private GetCosStsClient  getCosStsClient;

    @Operation(summary ="获取上传预签名url")
    @GetMapping
    public Result<?> getUrl(@RequestParam("img") String img){

        URL url = getCosStsClient.getUrl(img);

        return   Result.build(url, ResultCodeEnum.SUCCESS);
    }


}
