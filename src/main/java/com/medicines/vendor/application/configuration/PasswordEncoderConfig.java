package com.medicines.vendor.application.configuration;

import org.springframework.security.crypto.password.PasswordEncoder;

public interface PasswordEncoderConfig {
	PasswordEncoder passwordEncoder();
}
