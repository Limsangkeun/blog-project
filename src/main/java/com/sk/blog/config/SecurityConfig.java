package com.sk.blog.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Bean
	public PasswordEncoder encoder () {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private DataSource datasource;
	
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.jdbcAuthentication()
				.passwordEncoder(encoder())
				.rolePrefix("ROLE_")
				.dataSource(datasource)
				.usersByUsernameQuery("SELECT username, password, enabled FROM user WHERE username = ?")
				.authoritiesByUsernameQuery("SELECT username, role FROM user WHERE username = ?");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/user/login", "/user/join", "/bootstrap/**", "/js/**", "/css/**").permitAll()
				.anyRequest().authenticated().and()
			.formLogin()
				.loginPage("/user/login")
				.loginProcessingUrl("/user/loginProcess")
				.defaultSuccessUrl("/").and()
			.logout()
				.logoutUrl("/user/logout");
			
	}
}
