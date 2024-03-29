package com.app.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity // to enable spring sec frmwork support
@Configuration // to tell SC , this is config class containing @Bean methods
@EnableGlobalMethodSecurity(prePostEnabled = true) // To enable method level authorization support : pre n post
													// authorization
public class SecurityConfig {

	@Autowired
	private PasswordEncoder enc;

	@Autowired
	private JwtAuthenticationFilter jwtFilter;

	@Bean
	public SecurityFilterChain authorizeRequests(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers("/**").permitAll().anyRequest().authenticated().and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

}
