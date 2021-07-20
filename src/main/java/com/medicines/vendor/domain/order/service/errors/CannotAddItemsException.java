package com.medicines.vendor.domain.order.service.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class CannotAddItemsException extends RuntimeException {
	@Override
	public String getMessage() {
		return "Cannot add items to this order!";
	}
}
