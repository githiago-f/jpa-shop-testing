package com.medicines.vendor.domain.medicine;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@Data @NoArgsConstructor
@Builder @AllArgsConstructor
@Entity @Table(name = "datasheets")
public class Datasheet {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@JsonBackReference
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Medicine medicine;
	private String indication;

	@Column(name = "active_ingredient")
	private String activeIngredient;
}
