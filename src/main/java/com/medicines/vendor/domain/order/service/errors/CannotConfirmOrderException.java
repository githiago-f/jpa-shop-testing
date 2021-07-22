package com.medicines.vendor.domain.order.service.errors;

import com.medicines.vendor.shared.errors.BaseHttpException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CannotConfirmOrderException extends BaseHttpException {
	@Override
	public String getMessage() {
		return "Cannot confirm order because isn't waiting confirmation";
	}

	@Override
	public HttpStatus status() {
		return HttpStatus.BAD_REQUEST;
	}
}
