package com.tokendemo.tokendemo.Config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.tokendemo.tokendemo"})
public class TokendemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(TokendemoApplication.class, args);
	}

}
