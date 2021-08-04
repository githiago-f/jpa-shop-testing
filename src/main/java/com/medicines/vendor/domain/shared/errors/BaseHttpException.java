package com.medicines.vendor.domain.shared.errors;

import org.springframework.http.HttpStatus;

public abstract class BaseHttpException extends RuntimeException {
	public abstract HttpStatus status();

	public BaseHttpException() { }

	public BaseHttpException(String message) {
		super(message);
	}

	public BaseHttpException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseHttpException(Throwable cause) {
		super(cause);
	}

	public BaseHttpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}
}
