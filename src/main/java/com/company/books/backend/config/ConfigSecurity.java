package com.company.books.backend.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ConfigSecurity {
	
	 @Bean
	 public UserDetailsManager userDetailsManager(DataSource datasource) {
		 
		 return new JdbcUserDetailsManager(datasource);
	 }
	
	/*
	 * @Bean public InMemoryUserDetailsManager userDetailsManager() {
	 * 
	 * UserDetails user1 = User.builder() .username("user1")
	 * .password("{noop}user123") .roles("Employee") .build();
	 * 
	 * UserDetails user2 = User.builder() .username("user2")
	 * .password("{noop}user123") .roles("Employee", "Admin") .build();
	 * 
	 * return new InMemoryUserDetailsManager(user1, user2); }
	 */
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests( configure->{
			configure.requestMatchers(HttpMethod.GET, "/v1/categories").hasRole("Empleado").
			requestMatchers(HttpMethod.GET, "/v1/categories/**").hasRole("Empleado").
			requestMatchers(HttpMethod.POST, "/v1/categories").hasRole("Jefe").
			requestMatchers(HttpMethod.PUT, "/v1/categories/**").hasRole("Jefe").
			requestMatchers(HttpMethod.DELETE, "/v1/categories/**").hasRole("Jefe");
			
		}
		);
		
		http.httpBasic(Customizer.withDefaults());
		
		http.csrf( csrf -> csrf.disable());
		
		return http.build();
	}
}
