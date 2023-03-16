package com.coiffure;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import com.coiffure.controller.AuthController;

@SpringBootApplication
@EnableJpaAuditing

public class SalonCoiffureApplication {
	public static void main(String[] args) {
		SpringApplication.run(SalonCoiffureApplication.class, args);
		ac.firstAdminCreator();
	}
	static AuthController ac = new AuthController();
}