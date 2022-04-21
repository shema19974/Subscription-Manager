package com.sm;

import java.util.ArrayList;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sm.domain.Role;
import com.sm.domain.ServiceEligibility;
import com.sm.domain.User;
import com.sm.service.ServiceEligibiltyService;
import com.sm.service.UserService;


@SpringBootApplication
public class SubstriptionManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(SubstriptionManagerApplication.class, args);
	}
	
//	This will help running everything inside this method before the program starts
	@Bean
	CommandLineRunner run(UserService userService, ServiceEligibiltyService serviceEligbility) {
		return args ->{
			// Create Role
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));
			// Create users 
			userService.saveUser(new User(null, "Prince", "Musonerwa", "prince1997", "1234", new ArrayList<Role>()));
			userService.saveUser(new User(null, "Will", "Smith", "willy", "1234",new ArrayList<Role>()));
			userService.saveUser(new User(null, "Kayitare", "Elie", "ke", "1234", new ArrayList<Role>()));
			// Add roles to users 
			userService.addRoleToUser("prince1997", "ROLE_ADMIN");
			userService.addRoleToUser("willy", "ROLE_USER");
			userService.addRoleToUser("ke", "ROLE_USER");
			// Create eligibility criteria

			serviceEligbility.saveServiceEligibility(new ServiceEligibility(18, 1, 5500, 5000));
			
		    
		};
	}
		
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
