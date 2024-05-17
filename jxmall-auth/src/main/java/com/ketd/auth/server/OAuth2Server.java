package com.ketd.auth.server;

import com.ketd.common.result.Result;
import org.springframework.web.servlet.ModelAndView;

public interface OAuth2Server {
    Result<?> github(String code);

    Result<?> gitee(String code);
}
