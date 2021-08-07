package com.medicines.vendor.application.configuration;

import com.medicines.vendor.domain.users.repository.UserRepository;
import com.medicines.vendor.domain.users.service.ImplUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.medicines.vendor.application.security.ApplicationRole.LAB_ADMIN;
import static com.medicines.vendor.application.security.ApplicationRole.TECHNICAL;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	private final UserRepository userRepository;

	@Autowired
	public SecurityConfiguration(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.GET, "/api/v*/medicines/**", "/api/v*/laboratory/**")
				.permitAll()
			.antMatchers(HttpMethod.PUT,"/api/v*/laboratory/**")
				.hasAnyRole(LAB_ADMIN.name(), TECHNICAL.name())
			.antMatchers(HttpMethod.POST,"/api/v*/laboratory/**")
				.hasAnyRole(LAB_ADMIN.name(), TECHNICAL.name())
			.anyRequest()
			.authenticated()
			.and()
			.httpBasic();
	}

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		return new ImplUserDetailsService(userRepository);
	}
}
