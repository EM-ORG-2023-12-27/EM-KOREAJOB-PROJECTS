package com.example.jobKoreaIt.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class WebMvcConfig implements WebMvcConfigurer {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/js/**").addResourceLocations("classpath:/static/js/"); //.setCachePeriod(60*60*24*365);
		registry.addResourceHandler("/css/**").addResourceLocations("classpath:/static/css/"); //.setCachePeriod(60*60*24*365);
		registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/static/assets/"); //.setCachePeriod(60*60*24*365);
		registry.addResourceHandler("/font/**").addResourceLocations("classpath:/static/font/");//.setCachePeriod(60*60*24*365);
		registry.addResourceHandler("/favicon.ico").addResourceLocations("classpath:/static/"); // 추가된 부분
	}
	@Override // 컨버터추가 (나재현)
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		converters.add(new FormHttpMessageConverter());
		converters.add(new StringHttpMessageConverter());
	}

}