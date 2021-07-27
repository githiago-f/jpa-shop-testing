package com.medicines.vendor.application.security;

import lombok.Getter;

import static com.medicines.vendor.application.security.ApplicationPermissions.*;

@Getter
public enum ApplicationRole {
	ADMIN(READ_MEDICINE, WRITE_MEDICINE),
	CONSUMER(READ_MEDICINE);

	private final ApplicationPermissions[] permissions;

	ApplicationRole(ApplicationPermissions ...permissions) {
		this.permissions = permissions;
	}
}
