package com.nagaro.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//Authorization
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				.withUser("admin")
				.password("admin")
				.roles("ADMIN")
			.and()
				.withUser("user")
				.password("user")
				.roles("USER");
	}

	//Authentication 
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()			
			.antMatchers("/api/account/get/statement/by/dates","/api/account/get/statement/by/amoun").hasRole("ADMIN")			
			.antMatchers("api/account/get/last/three/month/statement").hasAnyRole("ADMIN","USER")
			.anyRequest().authenticated()
			.and()
			.httpBasic()
			.and()
			.exceptionHandling().accessDeniedPage("/403");
	}

	
	
}
