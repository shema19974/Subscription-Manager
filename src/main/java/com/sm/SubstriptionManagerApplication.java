package com.sm;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.sm.domain.Role;
import com.sm.domain.User;
import com.sm.service.UserService;

@SpringBootApplication
public class SubstriptionManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubstriptionManagerApplication.class, args);
	}
	
//	This will help running everything inside this method before the program starts
	@Bean
	CommandLineRunner run(UserService userService) {
		return args ->{
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			userService.saveRole(new Role(null, "ROLE_MANAGER"));
			
			userService.saveUser(new User(null, "Prince Musonerwa", "prince1997", "1234", new ArrayList()));
			userService.saveUser(new User(null, "Will Smith", "willy", "1234", new ArrayList()));
			userService.saveUser(new User(null, "Paul Kagame", "pk", "1234", new ArrayList()));
			
			userService.addRoleToUser("prince1997", "ROLE_USER");
			userService.addRoleToUser("pk", "ADMIN_USER");
			userService.addRoleToUser("willy", "ROLE_MANAGER");
					
			
		};
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
