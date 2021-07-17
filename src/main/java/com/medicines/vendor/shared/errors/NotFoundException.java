package com.medicines.vendor.shared.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseBody
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends IllegalStateException {
	public NotFoundException(String message) { super(message); }
}
