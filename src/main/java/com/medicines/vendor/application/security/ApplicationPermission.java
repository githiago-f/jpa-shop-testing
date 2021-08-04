package com.medicines.vendor.application.security;

import lombok.Getter;

@Getter
public enum ApplicationPermission {
	READ_MEDICINE("medicine:read"),
	READ_ALL_MEDICINE("medicine:read_all"),
	WRITE_MEDICINE("medicine:write"),
	WRITE_DATASHEET("datasheet:write"),
	READ_DATASHEET("datasheet:read"),
	ORDER_DRUG("order:write"),
	READ_ORDER("order:read");

	private final String permission;

	ApplicationPermission(String permission) {
		this.permission = permission;
	}
}
