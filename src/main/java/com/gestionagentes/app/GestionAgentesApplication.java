package com.gestionagentes.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@SpringBootApplication
public class GestionAgentesApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionAgentesApplication.class, args);
		
	}
	
	 @Bean
	    public WebMvcConfigurer corsConfigurer() 
	    {
	        return new WebMvcConfigurer() {
	            @Override
	            public void addCorsMappings(CorsRegistry registry) {
	                registry.addMapping("/**")
	                .allowedMethods("GET","POST","PUT","DELETE")	                
	                .allowedOrigins("http://localhost:4200");
	            }
	        };
	    }

}
