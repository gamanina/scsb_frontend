package com.scsb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.scsb.interceptor.NavbarInterceptor;


@Configuration
public class InterceptorWebMvcConfig implements WebMvcConfigurer 
{	
	@Bean
	public NavbarInterceptor customInterceptor() 
	{
		return new NavbarInterceptor();
	}
	
    final String[] excludePathPatterns = {"/assets/**","/login","/error/**","/ajax/**","/postLogin","/doLogin","/export/**"
    		,"/downtemp","/upload/**","/test/**"};
    
    @Override
    public void addInterceptors(InterceptorRegistry registry) 
    {
        registry.addInterceptor(customInterceptor())
        .addPathPatterns("/**").excludePathPatterns(excludePathPatterns);
    }
}