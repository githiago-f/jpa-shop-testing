package com.medicines.vendor.domain.order.dto;

import com.medicines.vendor.domain.order.Medicine;
import lombok.AllArgsConstructor;
import lombok.Builder;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
public class MedicineDTO {
	@NotEmpty
	private final String name;
	@DecimalMin(value = "0.01")
	@NotEmpty
	private final BigDecimal price;
	@NotEmpty
	private final String slug;

	public Medicine toEntity() {
		return Medicine.builder()
			.name(name)
			.price(price)
			.slug(slug)
			.build();
	}
}
