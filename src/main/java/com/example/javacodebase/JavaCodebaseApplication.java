package com.example.javacodebase;

import com.example.javacodebase.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class JavaCodebaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(JavaCodebaseApplication.class, args);
	}
}
