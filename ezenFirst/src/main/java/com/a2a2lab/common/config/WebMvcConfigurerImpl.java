package com.a2a2lab.common.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.a2a2lab.common.interceptor.CheckLoginSessionInterceptor;

@Configuration
public class WebMvcConfigurerImpl implements WebMvcConfigurer{

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new CheckLoginSessionInterceptor())
//				.order(1)
				.addPathPatterns("/*/*/Xdm/*", "/*/*/*/*Usr*") // /*/TableOrder/*
				.excludePathPatterns(
						
						"/loginXdm",
						"/signinXdmProc"
						
		);
	}

}
