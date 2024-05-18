package com.ketd.auth.server;

import com.ketd.common.result.Result;

public interface OAuth2Service {
    Result<?> github(String code);

    Result<?> gitee(String code);
}
