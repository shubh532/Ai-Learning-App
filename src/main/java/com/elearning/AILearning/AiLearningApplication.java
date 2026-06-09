package com.elearning.AILearning;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class AiLearningApplication {
	public static void main(String[] args) {
		SpringApplication.run(AiLearningApplication.class, args);
	}

}
