package com.medicines.vendor.application.security.errors;

import com.medicines.vendor.domain.shared.errors.BaseHttpException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class Unauthorized extends BaseHttpException {
	@Override
	public HttpStatus status() {
		return HttpStatus.UNAUTHORIZED;
	}

	public Unauthorized() {
		this("Unauthorized");
	}

	public Unauthorized(String message) {
		super(message);
	}
}
