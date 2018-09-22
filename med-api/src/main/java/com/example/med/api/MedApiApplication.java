package com.example.med.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.example.med.api.config.property.MedApiProperty;

@SpringBootApplication
@EnableConfigurationProperties(MedApiProperty.class)
public class MedApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedApiApplication.class, args);
	}
}


