package com.medicines.vendor.application.security;

import lombok.Getter;

@Getter
public enum ApplicationPermissions {
	READ_MEDICINE("medicine:read"),
	WRITE_MEDICINE("medicine:write"),
	OPEN_ORDER("order:open"),
	READ_ORDER("order:read");

	private final String permission;

	ApplicationPermissions(String permission) {
		this.permission = permission;
	}
}
