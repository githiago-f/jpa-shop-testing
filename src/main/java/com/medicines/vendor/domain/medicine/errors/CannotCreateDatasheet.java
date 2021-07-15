package com.medicines.vendor.domain.medicine.errors;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class CannotCreateDatasheet extends RuntimeException {
}
