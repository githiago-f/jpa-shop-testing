package com.medicines.vendor.domain.order.service.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MedicineInactiveException extends RuntimeException {
	@Override
	public String getMessage() {
		return "Medicine should be active to perform this action";
	}
}
