package com.medicines.vendor.shared.errors.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class ApiError {
	@JsonFormat(shape = JsonFormat.Shape.STRING, locale = "pt_BR")
	LocalDateTime timestamp;
	HttpStatus status;
	String message;
	String exception;
	List<SubError> errors;

	ApiError() {
		timestamp = LocalDateTime.now();
	}

	public ApiError(HttpStatus status) {
		this();
		this.status = status;
	}

	public ApiError(HttpStatus status, Throwable ex) {
		this();
		this.status = status;
		this.message = "Unexpected error";
		this.exception = ex.getLocalizedMessage();
	}

	public ApiError(HttpStatus status, String message, Throwable ex) {
		this();
		this.status = status;
		this.message = message;
		this.exception = ex.getLocalizedMessage();
	}

	public void addErrors(SubError subError) {
		this.errors.add(subError);
	}
}
