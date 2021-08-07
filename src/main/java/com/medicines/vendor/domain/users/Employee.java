package com.medicines.vendor.domain.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.medicines.vendor.application.security.ApplicationUserDetails;
import com.medicines.vendor.domain.laboratory.Laboratory;
import com.medicines.vendor.domain.users.vo.EmployeeData;
import com.medicines.vendor.domain.users.vo.UserCpfMask;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import java.util.UUID;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Employee extends ApplicationUserDetails {
	@CPF
	@Column(unique = true)
	private String cpf;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Laboratory laboratory;
	private UUID hirer;

	public Employee(EmployeeData employeeData, Laboratory laboratory) {
		super(
			employeeData.getUsername(),
			employeeData.getPassword(),
			employeeData.getRole(),
			employeeData.getFullName(),
			employeeData.getCpf()
		);
		this.laboratory = laboratory;
		this.hirer = laboratory.getId();
	}

	public String getUsername() {
		return super.getUsername();
	}

	public String getCpf() {
		return UserCpfMask.applyMask(cpf);
	}
}
