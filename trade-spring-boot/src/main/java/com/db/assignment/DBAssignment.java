package com.db.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class DBAssignment {

	public static void main(String[] args) {
		SpringApplication.run(DBAssignment.class, args);
	}

}
