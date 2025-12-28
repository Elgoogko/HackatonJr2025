package com.main.hackatonjr;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class HackatonjrApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(HackatonjrApplication.class, args);
        Runner runner = context.getBean(Runner.class); //Injection Spring
        runner.start();
		context.close();
	}
}
