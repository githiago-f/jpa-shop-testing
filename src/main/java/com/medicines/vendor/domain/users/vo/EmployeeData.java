package com.medicines.vendor.domain.users.vo;

import com.medicines.vendor.application.security.ApplicationRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeeData {
	String fullName;
	@CPF
	String cpf;
	@Email
	String username;
	String password;
	ApplicationRole role;
}
