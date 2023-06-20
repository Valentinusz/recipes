package com.valentinusz.recipes;

import com.github.javafaker.Faker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class RecipesApplication {
	@Bean
    @Profile("development")
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
    @Profile("development")
	public Faker faker() {
		return new Faker();
	}

	public static void main(String[] args) {
		SpringApplication.run(RecipesApplication.class, args);
	}
}
