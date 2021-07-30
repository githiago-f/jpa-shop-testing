package com.medicines.vendor.application.security;

import lombok.Getter;

@Getter
public enum ApplicationPermission {
	READ_MEDICINE("medicine:read"),
	WRITE_MEDICINE("medicine:write"),
	OPEN_ORDER("order:open"),
	READ_ORDER("order:read");

	private final String permission;

	ApplicationPermission(String permission) {
		this.permission = permission;
	}
}
