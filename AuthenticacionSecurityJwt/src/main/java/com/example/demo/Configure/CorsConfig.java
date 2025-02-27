package com.example.demo.Configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig  implements WebMvcConfigurer{

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		 
		registry.addMapping("/auth/**")
		.allowedOrigins("http://localhost:3001/")
		.allowedMethods("GET","POST","PUT","DELETE","PATCH")
		.allowCredentials(true);
	
	}
	
	
	


}
