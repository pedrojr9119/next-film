package com.nextfilme;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class NextFilmeApplication extends WebMvcConfigurerAdapter {

	public static void main(String[] args) throws Exception {
		new SpringApplicationBuilder(NextFilmeApplication.class).run(args);
	}
}
