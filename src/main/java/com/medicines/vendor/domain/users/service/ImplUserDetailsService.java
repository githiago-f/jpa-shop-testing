package com.medicines.vendor.domain.users.service;

import com.medicines.vendor.application.security.ApplicationUserDetails;
import com.medicines.vendor.domain.users.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class ImplUserDetailsService implements UserDetailsService {
	private static final Logger log = LogManager.getLogger(ImplUserDetailsService.class.getName());
	private final UserRepository userRepository;

	@Autowired
	public ImplUserDetailsService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.debug(username + "Has logged in");
		return userRepository.findByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("Username or password is incorrect"));
	}
}
