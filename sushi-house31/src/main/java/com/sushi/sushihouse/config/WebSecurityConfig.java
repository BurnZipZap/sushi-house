package com.sushi.sushihouse.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource)
				.usersByUsernameQuery(
						"SELECT username, password, enabled from users where username = ?")
				.authoritiesByUsernameQuery(
						"SELECT u.username, a.authority FROM authorities a, users u "
						+ "WHERE u.username = ? AND u.id = a.id_users");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				.antMatchers("/").permitAll()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/cook/**").hasAnyRole("COOK", "ADMIN")
				.antMatchers("/delivery/**").hasAnyRole("DELIVERY", "ADMIN")
				.antMatchers("/manager/**").hasAnyRole("MANAGER", "ADMIN")
				.antMatchers("/purchase/**").hasAnyRole("PURCHASE", "ADMIN")
			.and()
				.formLogin().loginPage("/showMyLoginPage")
				.loginProcessingUrl("/authenticateTheUser").permitAll()
			.and()
				.logout().permitAll()
				.logoutSuccessUrl("/")
			.and()
				.exceptionHandling().accessDeniedPage("/access-denied");
	}

}