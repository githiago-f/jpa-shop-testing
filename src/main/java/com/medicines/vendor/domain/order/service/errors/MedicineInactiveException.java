package com.medicines.vendor.domain.order.service.errors;

import com.medicines.vendor.domain.shared.errors.BaseHttpException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class MedicineInactiveException extends BaseHttpException {
	@Override
	public String getMessage() {
		return "Medicine should be active to perform this action";
	}

	@Override
	public HttpStatus status() {
		return HttpStatus.BAD_REQUEST;
	}
}
