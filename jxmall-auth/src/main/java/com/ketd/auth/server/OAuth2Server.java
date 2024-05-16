package com.ketd.auth.server;

import org.springframework.web.servlet.ModelAndView;

public interface OAuth2Server {
    ModelAndView github(String code);
}
