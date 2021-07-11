package com.medicines.vendor.domain.users.dto;

import com.medicines.vendor.domain.users.Consumer;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotEmpty;

@NoArgsConstructor
@AllArgsConstructor
public class ConsumerDTO {
	@NotEmpty
	private String name;
	@CPF
	private String cpf;
	public Consumer toModel() {
		return Consumer.builder()
			.CPF(cpf)
			.name(name)
			.build();
	}
}
