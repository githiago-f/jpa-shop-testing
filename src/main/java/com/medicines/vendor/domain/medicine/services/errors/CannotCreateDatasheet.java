package com.medicines.vendor.domain.medicine.services.errors;

import com.medicines.vendor.domain.shared.errors.BaseHttpException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class CannotCreateDatasheet extends BaseHttpException {
	@Override
	public HttpStatus status() {
		return HttpStatus.UNPROCESSABLE_ENTITY;
	}
}
