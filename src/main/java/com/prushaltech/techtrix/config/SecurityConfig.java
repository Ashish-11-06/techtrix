package com.prushaltech.techtrix.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.prushaltech.techtrix.service.CustomUserDetailsService;
import com.prushaltech.techtrix.util.JwtUtil;

@Configuration
public class SecurityConfig {

	@Autowired
	private CustomUserDetailsService customUserDetailsService;

	@Autowired
	private JwtUtil jwtUtil;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
			throws Exception {
		return authenticationConfiguration.getAuthenticationManager();
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	    http.authorizeRequests(auth -> auth.requestMatchers(new AntPathRequestMatcher("/api/users/add")).permitAll()) 
        .authorizeRequests(auth -> auth.requestMatchers(new AntPathRequestMatcher("/api/auth/login")).permitAll())  
        .csrf().disable()
        .sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS);  

		http.addFilterAfter(jwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	// @Bean
	public JwtRequestFilter jwtRequestFilter() {
		return new JwtRequestFilter(jwtUtil, customUserDetailsService);
	}

}
