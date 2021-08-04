package com.medicines.vendor.domain.users;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.medicines.vendor.application.security.ApplicationUserDetails;
import com.medicines.vendor.domain.laboratory.Laboratory;
import com.medicines.vendor.domain.users.vo.EmployeeData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
public class Employee extends ApplicationUserDetails {
	private String fullName;
	@CPF
	@Column(unique = true)
	private String cpf;

	@JsonBackReference
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Laboratory laboratory;
	private String hirer;

	public Employee(EmployeeData employeeData, Laboratory laboratory) {
		super(employeeData.getUsername(), employeeData.getPassword(), employeeData.getRole());
		this.fullName = employeeData.getFullName();
		this.laboratory = laboratory;
		this.cpf = employeeData.getCpf();
		this.hirer = laboratory.getCnpj();
	}

	public String getUsername() {
		return super.getUsername();
	}
}
