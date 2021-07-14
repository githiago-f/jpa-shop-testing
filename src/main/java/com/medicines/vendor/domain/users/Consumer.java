package com.medicines.vendor.domain.users;

import com.medicines.vendor.domain.users.vo.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;

@Getter
@AllArgsConstructor @NoArgsConstructor @Builder
@Entity
@Table(name = "consumers")
public class Consumer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;

	@CPF
	@Column(name = "cpf", unique = true, updatable = false)
	private String CPF;

	@Enumerated(EnumType.STRING)
	@Column(name = "type", updatable = false)
	private final UserType type = UserType.CONSUMER;
}
