package com.medicines.vendor.application.security;

public class SecurityURLPatterns {
	public static final String[] labOnly = {
		"/api/v*/providers/**"
	};

	public static final String[] consumerOnly = {};

	public static final String[] all = {
		"/",
		"/api/v*/medicines/**"
	};
}
