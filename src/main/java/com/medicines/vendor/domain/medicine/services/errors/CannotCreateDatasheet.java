package com.medicines.vendor.domain.medicine.services.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
public class CannotCreateDatasheet extends RuntimeException {
}
