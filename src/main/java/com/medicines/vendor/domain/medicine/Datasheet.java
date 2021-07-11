package com.medicines.vendor.domain.medicine;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @NoArgsConstructor
@Entity
@Table(name = "datasheets")
public class Datasheet {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne(fetch = FetchType.LAZY)
	public Medicine medicine;

	public Datasheet(Medicine medicine){
		this.medicine = medicine;
	}
}
