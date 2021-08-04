package com.medicines.vendor.domain.shared.errors.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class ApiValidationError extends SubError {
	private final String object;
	private final String field;
	private Object rejectedValue;
	private String message;

	public ApiValidationError(String object, String field) {
		this.object = object;
		this.field = field;
	}
}
