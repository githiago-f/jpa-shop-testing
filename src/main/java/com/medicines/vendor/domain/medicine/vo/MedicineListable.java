package com.medicines.vendor.domain.medicine.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class MedicineListable {
	private final String code;
	private final String name;

	@Enumerated(EnumType.STRING)
	private final MedicineState state;

	@JsonProperty(value = "created_at")
	@JsonFormat(shape = JsonFormat.Shape.STRING, locale = "pt_BR")
	private final LocalDateTime createdAt;
}
