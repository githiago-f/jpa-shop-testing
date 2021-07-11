package com.medicines.vendor.domain.medicine.dto;

import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.order.vo.MedicineState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Builder
@AllArgsConstructor
public class MedicineDTO {
	@NotEmpty
	private final String code;
	@NotEmpty
	private final String name;
	@DecimalMin(value = "0.01")
	@NotNull
	@NumberFormat(style = NumberFormat.Style.CURRENCY)
	private final BigDecimal price;

	public Medicine toEntity() {
		return Medicine.builder()
			.code(code)
			.state(MedicineState.DATASHEET)
			.name(name)
			.price(price)
			.build();
	}
}
