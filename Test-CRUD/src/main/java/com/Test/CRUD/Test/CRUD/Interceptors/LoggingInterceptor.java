package com.Test.CRUD.Test.CRUD.Interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;

public class LoggingInterceptor implements HandlerInterceptor {
    private final Logger LOG = LoggerFactory.getLogger(LoggingInterceptor.class);
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOG.info("Request URL: {}",request.getRequestURL());
        LOG.info("Request Method: {}",request.getMethod());
        return true;
    }
}
