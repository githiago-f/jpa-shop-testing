package com.medicines.vendor.domain.provider;

import lombok.*;
import org.hibernate.validator.constraints.br.CNPJ;

import javax.persistence.*;
import java.util.List;

@Data @Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "providers")
public class Laboratory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@CNPJ
	@Column(name = "cnpj", unique = true)
	private String CNPJ;
	private String name;

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Employee> employees;
}
