package com.Test.CRUD.Test.CRUD.Config;

import com.Test.CRUD.Test.CRUD.Interceptors.GeneralInterceptor;
import com.Test.CRUD.Test.CRUD.Interceptors.LoggingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    //@Override
    //public void addInterceptors(InterceptorRegistry registry) {
    //    //GeneralInterceptor "vede tutti i dati che entrano e li controlla 3 volte"
    //    registry.addInterceptor(new GeneralInterceptor());
    //    // LoggingInterceptor ""
    //    registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/**");
    //}
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        //registry.addInterceptor(new GeneralInterceptor());
        //registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/**");
    }
}
