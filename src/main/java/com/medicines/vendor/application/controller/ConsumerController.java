package com.medicines.vendor.application.controller;

import com.medicines.vendor.domain.users.Consumer;
import com.medicines.vendor.domain.users.dto.ConsumerDTO;
import com.medicines.vendor.domain.users.services.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

@RestController
@RequestMapping("api/customers")
public class ConsumerController {
	private final ConsumerService consumerService;

	@Autowired
	public ConsumerController(ConsumerService consumerService) {
		this.consumerService = consumerService;
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable("id") Long id) {
		return consumerService.getConsumer(id)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntityModel<Consumer> createOne(@Valid @RequestBody ConsumerDTO consumerDTO) {
		return consumerService.saveConsumer(consumerDTO.toEntity());
	}
}
