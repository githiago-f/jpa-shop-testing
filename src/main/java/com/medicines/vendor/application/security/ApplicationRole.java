package com.medicines.vendor.application.security;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static com.medicines.vendor.application.security.ApplicationPermission.*;

@Getter
public enum ApplicationRole {
	LAB_ADMIN(
		WRITE_DATASHEET,
		READ_MEDICINE,
		WRITE_MEDICINE,
		READ_ALL_MEDICINE,
		READ_ORDER
	),
	TECHNICAL(WRITE_DATASHEET, READ_MEDICINE),
	VENDOR_ADMIN(READ_MEDICINE, READ_DATASHEET, READ_ORDER, ORDER_DRUG),
	CONSUMER(READ_MEDICINE, READ_DATASHEET);

	private final Set<ApplicationPermission> permissions;

	ApplicationRole(ApplicationPermission...permissions) {
		this.permissions = new HashSet<>(Arrays.asList(permissions));
	}

	public Set<SimpleGrantedAuthority> getGrantedAuthorities() {
		Set<SimpleGrantedAuthority> authorities = new HashSet<>();
		for (ApplicationPermission permission : getPermissions()) {
			authorities.add(new SimpleGrantedAuthority(permission.getPermission()));
		}
		authorities.add(
			new SimpleGrantedAuthority("ROLE_" + this.name())
		);
		return authorities;
	}
}
