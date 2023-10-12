package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutionException;

@SpringBootApplication
public class AuiApplication {

	public static void main(String[] args) throws ExecutionException, InterruptedException {
		SpringApplication.run(AuiApplication.class, args);
	}
}
