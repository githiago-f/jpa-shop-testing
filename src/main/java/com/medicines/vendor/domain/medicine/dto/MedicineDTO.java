package com.medicines.vendor.domain.medicine.dto;

import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.vo.MedicineState;
import com.medicines.vendor.shared.validators.Unique;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.format.annotation.NumberFormat;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Builder
@AllArgsConstructor
public class MedicineDTO {
	@NotEmpty
	@Unique(entity = Medicine.class, field = "code")
	private final String code;

	@NotEmpty
	private final String name;

	@DecimalMin(value = "0.01")
	@NotNull
	@NumberFormat(style = NumberFormat.Style.CURRENCY)
	private final BigDecimal price;

	public Medicine toCreateEntity() {
		return Medicine.builder()
			.code(code)
			.state(MedicineState.DATASHEET_REQUIRED)
			.name(name)
			.price(price)
			.build();
	}
}
