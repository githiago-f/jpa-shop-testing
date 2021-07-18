package com.medicines.vendor.domain.medicine.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.medicines.vendor.domain.medicine.vo.MedicineState;
import lombok.Getter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDateTime;

@Getter
public class MedicineListable {
	private final String code;
	private final String name;
	@Enumerated(EnumType.STRING)
	private final MedicineState state;
	@JsonProperty(value = "created_at")
	@JsonFormat(shape = JsonFormat.Shape.STRING, locale = "pt_BR")
	private final LocalDateTime createdAt;

	public MedicineListable(String code, String name, MedicineState state, LocalDateTime createdAt) {
		this.code = code;
		this.name = name;
		this.state = state;
		this.createdAt = createdAt;
	}
}
