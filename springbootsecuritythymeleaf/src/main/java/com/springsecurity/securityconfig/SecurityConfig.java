package com.springsecurity.securityconfig;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
	
	//add support for jdbc
	
	@Bean
	public UserDetailsManager userDetailsManager(DataSource dataSource) {
		
		//return new JdbcUserDetailsManager(dataSource);
		
		//adding custom tables quries
		JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
		
		//define query to retrieve a user by username
		jdbcUserDetailsManager.setUsersByUsernameQuery(
				"select user_id, pw, active from members where user_id=?");
		
		//define query to retrieve the authorities/roles by username
		jdbcUserDetailsManager.setAuthoritiesByUsernameQuery(
				"select user_id, role from roles where user_id=?");
		
		return jdbcUserDetailsManager;
		
	}
	
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		
		http.authorizeHttpRequests(configurer ->		
			configurer
						.requestMatchers("/").hasRole("EMPLOYEE")
						.requestMatchers("/leaders/**").hasRole("MANAGER")
						.requestMatchers("/systems/**").hasRole("ADMIN")
						.anyRequest().authenticated()
						
		)
			.formLogin(form -> 
				form
						.loginPage("/showMyLoginPage")
						.loginProcessingUrl("/authenticateTheUser")
						.permitAll()			
			)
				.logout(logout -> logout.permitAll()
				
			)
				.exceptionHandling(configurer ->
						configurer.accessDeniedPage("/access-denied")
			);
		
		return http.build();
	}
	
	
	
	
	/*	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		UserDetails john = User.builder()
				.username("john")
				.password("{noop}password0987")
				.roles("EMPLOYEE")
				.build();
		
		UserDetails mary = User.builder()
				.username("mary")
				.password("{noop}password0987")
				.roles("EMPLOYEE", "MANAGER")
				.build();
		
		UserDetails susan = User.builder()
				.username("susan")
				.password("{noop}password0987")
				.roles("EMPLOYEE", "MANAGER", "ADMIN")
				.build();
		
		return new InMemoryUserDetailsManager(john, mary, susan);
	}
*/


}
