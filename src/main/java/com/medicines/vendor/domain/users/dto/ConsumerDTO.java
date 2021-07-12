package com.medicines.vendor.domain.users.dto;

import com.medicines.vendor.domain.users.Consumer;
import com.medicines.vendor.shared.validators.Unique;
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
	@Unique(entity = Consumer.class, field = "cpf")
	private String cpf;
	public Consumer toModel() {
		return Consumer.builder()
			.CPF(cpf)
			.name(name)
			.build();
	}
}
