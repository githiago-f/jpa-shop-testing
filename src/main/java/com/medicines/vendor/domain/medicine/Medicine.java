package com.medicines.vendor.domain.medicine;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.medicines.vendor.domain.medicine.vo.MedicineState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Builder @NoArgsConstructor @AllArgsConstructor
@Entity
@Table(name = "medicines")
public class Medicine {
	@Id
	private String code;
	@Column(name = "commercial_name")
	private String name;
	@Column(name = "factory_price")
	private BigDecimal price;
	@Enumerated(EnumType.STRING)
	private MedicineState state;

	@JsonFormat(shape = JsonFormat.Shape.STRING, locale = "pt_BR")
	@Column(name = "created_at")
	private LocalDate createdAt;

	@PrePersist()
	void prePersist() {
		createdAt = LocalDate.now();
	}
}
