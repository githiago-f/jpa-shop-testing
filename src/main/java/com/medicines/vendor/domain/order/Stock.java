package com.medicines.vendor.domain.order;

import com.medicines.vendor.domain.medicine.Medicine;
import lombok.*;

import javax.persistence.*;

@Getter @Builder @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "stocks")
public class Stock {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne
	private Medicine medicine;
	private Integer quantity;
}
