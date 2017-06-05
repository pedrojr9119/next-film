package com.nextfilme.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/index").setViewName("index");
		registry.addViewController("/").setViewName("index");
		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/listagem").setViewName("listagem");
		registry.addViewController("/login").setViewName("login");
		registry.addViewController("/video").setViewName("video");
	}

}