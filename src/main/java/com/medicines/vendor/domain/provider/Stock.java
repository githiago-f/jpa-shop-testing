package com.medicines.vendor.domain.provider;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.medicines.vendor.domain.medicine.Medicine;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "stock")
public class Stock {
	private Long id;
	private Integer quantity;
	private boolean active;
	private Medicine medicine;
	@JsonFormat(shape = JsonFormat.Shape.STRING, locale = "pt_BR")
	private LocalDateTime updatedAt;
}
