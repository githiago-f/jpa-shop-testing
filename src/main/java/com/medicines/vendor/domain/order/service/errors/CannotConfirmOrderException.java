package com.medicines.vendor.domain.order.service.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Isn't waiting confirmation")
public class CannotConfirmOrderException extends IllegalStateException {
	@Override
	public String getMessage() {
		return "Cannot confirm order because isn't waiting confirmation";
	}
}
