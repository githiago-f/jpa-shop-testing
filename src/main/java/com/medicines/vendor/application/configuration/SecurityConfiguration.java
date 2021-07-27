package com.medicines.vendor.application.configuration;

import static com.medicines.vendor.application.security.ApplicationRole.*;
import static com.medicines.vendor.application.security.SecurityURLPatterns.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private final PasswordEncoder passwordEncoder;

	@Autowired
	public SecurityConfiguration(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers(all).permitAll()
				.antMatchers(labOnly).hasRole(ADMIN.name())
				.antMatchers(consumerOnly).hasRole(CONSUMER.name())
			.anyRequest().authenticated()
			.and().httpBasic();
	}

	@Override
	protected UserDetailsService userDetailsService() {
		UserDetails thiago = User.builder()
			.username("thiago")
			.password(passwordEncoder.encode("pass123"))
			.roles(CONSUMER.name())
			.build();

		UserDetails manuel = User.builder()
			.username("manuel")
			.password(passwordEncoder.encode("pass123"))
			.roles(ADMIN.name())
			.build();

		return new InMemoryUserDetailsManager(
			thiago,
			manuel
		);
	}

	public PasswordEncoder getPasswordEncoder() {
		return passwordEncoder;
	}
}
