package com.medicines.vendor.application.controller;

import com.medicines.vendor.domain.shared.errors.BaseHttpException;
import com.medicines.vendor.domain.shared.errors.dto.ApiError;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionController extends ResponseEntityExceptionHandler {
	public ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
		return new ResponseEntity<>(apiError, apiError.getStatus());
	}

	@ExceptionHandler(BaseHttpException.class)
	protected ResponseEntity<Object> handleNotFoundException(BaseHttpException ex) {
		ApiError error = new ApiError(ex.status(), ex.getMessage(), ex);
		return buildResponseEntity(error);
	}

	@ExceptionHandler(UsernameNotFoundException.class)
	protected ResponseEntity<Object> handleUsernameNotFoundException(UsernameNotFoundException ex) {
		ApiError error = new ApiError(HttpStatus.UNAUTHORIZED, ex.getMessage(), ex);
		return buildResponseEntity(error);
	}
}
