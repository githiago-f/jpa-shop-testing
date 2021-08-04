package com.medicines.vendor.domain.medicine.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.medicines.vendor.domain.medicine.Datasheet;
import com.medicines.vendor.domain.shared.validators.Unique;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
public class DatasheetDTO {
	@NotEmpty
	@Length(min = 5, max = 5000)
	private String indication;
	@NotEmpty
	@JsonProperty("active_ingredient")
	private String activeIngredient;

	@NotEmpty
	@JsonProperty("medicine_code")
	@Unique(entity = Datasheet.class, field = "medicine.code")
	private String medicineCode;

	public Datasheet.DatasheetBuilder toEntity() {
		return Datasheet.builder()
			.activeIngredient(activeIngredient)
			.indication(indication);
	}
}
