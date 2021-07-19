package com.medicines.vendor.domain.order.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data @Builder
@AllArgsConstructor
public class OrderItemDTO {
	@JsonProperty("medicine_code")
	private String medicineCode;
	private Integer quantity;
}
