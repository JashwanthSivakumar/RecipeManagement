package com.example.recipe_rest_api.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer.UserDetailsBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
@Configuration
@EnableWebSecurity
public class SecurityConfig {
	@SuppressWarnings("removal")
	@Bean
	protected SecurityFilterChain configure(HttpSecurity http) throws Exception{
	http.csrf().disable()
		.authorizeRequests()
		.requestMatchers("/category**").permitAll()
		.requestMatchers("/recipe/**").hasRole("USER")
		.anyRequest().authenticated()
		.and().httpBasic();
		return http.build();
	}
	@Bean
	public UserDetailsService userDetailService() {
		UserDetails demoUser = User.builder().username("user").password(passwordEncoder().encode("user"))
				.roles("USER").build();
		
		UserDetails admin = User.builder().username("admin").password(passwordEncoder().encode("admin"))
				.roles("ADMIN").build();
		
		return new InMemoryUserDetailsManager(demoUser,admin);
		}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
