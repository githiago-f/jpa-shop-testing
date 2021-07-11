package com.medicines.vendor.domain.medicine.dto;

import com.medicines.vendor.domain.medicine.Medicine;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
public class MedicineDTO {
	@NotEmpty
	private final String code;
	@NotEmpty
	private final String name;
	@DecimalMin(value = "0.01")
	@NotEmpty
	private final BigDecimal price;

	public Medicine toEntity() {
		return Medicine.builder()
			.name(name)
			.price(price)
			.build();
	}
}
