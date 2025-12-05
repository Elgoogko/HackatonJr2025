package com.main.hackatonjr;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.main.*;

@SpringBootApplication
public class HackatonjrApplication {
	public static void main(String[] args) {
		SpringApplication.run(HackatonjrApplication.class, args);
		Runner runner = new Runner();
		runner.start();
	}
}
