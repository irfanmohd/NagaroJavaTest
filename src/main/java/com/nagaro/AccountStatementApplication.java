package com.nagaro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;

import com.nagaro.exception.GlobalExceptionHandler;

@SuppressWarnings("deprecation")
@SpringBootApplication
@Import(GlobalExceptionHandler.class)
public class AccountStatementApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(AccountStatementApplication.class, args);
	}


}
