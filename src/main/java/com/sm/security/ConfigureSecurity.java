package com.sm.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sm.filter.CustomUserAuthorization;
import com.sm.filter.CustomUserFilter;

import lombok.AllArgsConstructor;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class ConfigureSecurity extends WebSecurityConfigurerAdapter{
	private final UserDetailsService userDetailsService;
	private final BCryptPasswordEncoder byBCryptPasswordEncoder;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(userDetailsService).passwordEncoder(byBCryptPasswordEncoder);
		super.configure(auth);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// disable the cross-site request forgery
		//http.cors();
		http.csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		//http.cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues());
//		http.authorizeRequests().antMatchers("/login").permitAll();
//		http.authorizeRequests().antMatchers("/api/users/**").permitAll();
//		//http.authorizeRequests().antMatchers("/api/services/**").hasAnyRole("ROLE_ADMIN");
//		//http.authorizeRequests().antMatchers(HttpMethod.GET, "/api/services/**").hasAnyAuthority("ROLE_USER");
//		http.authorizeRequests().antMatchers("/api/checker").permitAll();
//		http.authorizeRequests().anyRequest().authenticated();
//		http.addFilter(new CustomUserFilter(authenticationManagerBean()));
		// Make sure that the customUserAuthorization runs before
		http.addFilterBefore(new CustomUserAuthorization(), UsernamePasswordAuthenticationFilter.class);
		http.httpBasic();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManager();
	}
	
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**")
				.allowedOrigins("*")
				.allowedMethods("GET", "PUT", "POST", "PATCH", "DELETE", "OPTIONS");
			}
		};
	}
}
