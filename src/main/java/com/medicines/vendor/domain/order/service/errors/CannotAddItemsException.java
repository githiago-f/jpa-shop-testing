package com.medicines.vendor.domain.order.service.errors;

import com.medicines.vendor.domain.shared.errors.BaseHttpException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_GATEWAY)
public class CannotAddItemsException extends BaseHttpException {
	@Override
	public String getMessage() {
		return "Cannot add items to this order!";
	}

	@Override
	public HttpStatus status() {
		return HttpStatus.BAD_GATEWAY;
	}
}
