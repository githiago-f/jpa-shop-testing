package com.medicines.vendor.domain.payment;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "taxes")
public class Tax {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private BigDecimal percentage;
}
