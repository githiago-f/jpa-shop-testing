package com.medicines.vendor.domain.shared.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends BaseHttpException {
	public ResourceAlreadyExistsException(String message) {
		super(message);
	}

	@Override
	public HttpStatus status() {
		return HttpStatus.CONFLICT;
	}
}
