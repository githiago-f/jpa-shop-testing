package com.medicines.vendor.domain.users;

import com.medicines.vendor.application.security.ApplicationUserDetails;
import com.medicines.vendor.application.security.ApplicationRole;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class Customer extends ApplicationUserDetails {
	private String name;
	private String cpf;

	public Customer(String email, String password) {
		super(email, password, ApplicationRole.CONSUMER);

	}
}
