package com.ketd.search.controller;

import com.ketd.common.result.Result;
import com.ketd.search.serveice.SearchService;
import com.ketd.search.vo.SearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.controller
 * @Author: ketd
 * @CreateTime: 2024-05-09  13:16
 */
@RequestMapping("/search/search")
@RestController
public class SearchController {

    @Autowired
    private SearchService  searchService;

    @PostMapping("/search")
    public Result<?> search(@RequestBody SearchParam searchParam) throws IOException {
        return searchService.search(searchParam);
    }
}
