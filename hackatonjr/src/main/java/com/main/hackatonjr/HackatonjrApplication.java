package com.main.hackatonjr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.main.hackatonjr.*;

@SpringBootApplication
public class HackatonjrApplication {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(HackatonjrApplication.class, args);
        Runner runner = context.getBean(Runner.class); //Injection Spring
        runner.start();
		context.close();
	}
}
