package com.ketd.search.serveice;

import com.ketd.common.result.Result;
import com.ketd.search.vo.SearchParam;

import java.io.IOException;

/**
 * @Description:
 * @BelongsProject: jxmall
 * @BelongsPackage: com.ketd.serveice
 * @Author: ketd
 * @CreateTime: 2024-05-09  13:17
 */
public interface SearchService{
    Result<?> search(SearchParam searchParam) throws IOException;
}
