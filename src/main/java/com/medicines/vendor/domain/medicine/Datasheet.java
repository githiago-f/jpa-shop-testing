package com.medicines.vendor.domain.medicine;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @NoArgsConstructor
@Builder @AllArgsConstructor
@Entity @Table(name = "datasheets")
public class Datasheet {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Medicine medicine;
	private Integer version;
	private String indication;

	@Column(name = "active_ingredient")
	private String activeIngredient;
}
