package com.medicines.vendor.domain.laboratory.service.errors;

import com.medicines.vendor.domain.shared.errors.BaseHttpException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FORBIDDEN)
public class NotEmployee extends BaseHttpException {
	public NotEmployee() {
		this("You have no permissions at this laboratory.");
	}

	public NotEmployee(String message) {
		super(message);
	}

	@Override
	public HttpStatus status() {
		return HttpStatus.FORBIDDEN;
	}
}
