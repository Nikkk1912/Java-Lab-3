package org.example.lab3;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class Lab3Application {
	private static final Logger log = LoggerFactory.getLogger(Lab3Application.class);

	public static void main(String[] args) {
		SpringApplication.run(Lab3Application.class, args);
	}

}
