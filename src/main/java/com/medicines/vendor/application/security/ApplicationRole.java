package com.medicines.vendor.application.security;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.HashSet;
import java.util.Set;

import static com.medicines.vendor.application.security.ApplicationPermission.*;

@Getter
public enum ApplicationRole {
	ADMIN(READ_MEDICINE, WRITE_MEDICINE),
	CONSUMER(READ_MEDICINE);

	private final Set<ApplicationPermission> permissions;

	ApplicationRole(ApplicationPermission...permissions) {
		this.permissions = Set.of(permissions);
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
