package com.Test.CRUD.Test.CRUD.Interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.logging.Logger;

public class GeneralInterceptor implements HandlerInterceptor {

    private final Logger LOG = (Logger) LoggerFactory.getLogger(GeneralInterceptor.class);

    //dobbiamo pensare che il Interceptors sono e la Dogana del nostro codice,possno controllare tutte le cose che entrano
    //assicurarsi che possono veramente entrare e in casi posso anche vedere cosa stanno portatando.
    @Override
    //il "preHandle" succede prima che il request raggiunga il "handler" cioè il controller, in questo caso "SudentController".
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        LOG.info("In preHandle method of genralInterceptor");
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    @Override
    //il "postHandle" succede dopo che il request raggiunga il "handler" cioè il controller, in questo caso "SudentController".
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
        LOG.info("In postHandle method of genralInterceptor");
    }

    @Override
    //il "afterCompletion" succede dopo il completamento del request al "handler" cioè il controller, in questo caso "SudentController".
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
        LOG.info("In afterCompletion method of genralInterceptor");
    }
}
