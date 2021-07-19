package com.medicines.vendor.domain.order.service.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NoItemsInOrderException extends IllegalStateException {
	@Override
	public String getMessage() {
		return "Should have at last one item in order";
	}
}
