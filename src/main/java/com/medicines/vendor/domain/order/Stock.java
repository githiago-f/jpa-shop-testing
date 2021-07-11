package com.medicines.vendor.domain.order;

import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.users.Provider;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
	@ManyToOne(fetch = FetchType.LAZY)
	private Provider provider;
}
