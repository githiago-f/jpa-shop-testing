package com.medicines.vendor.application.controller;

import com.medicines.vendor.domain.resource_models.ConsumerRepresentationModelAssembler;
import com.medicines.vendor.domain.users.Consumer;
import com.medicines.vendor.domain.users.dto.ConsumerDTO;
import com.medicines.vendor.domain.users.repository.ConsumerRepository;
import com.medicines.vendor.shared.errors.HandleUniqueFieldViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import javax.validation.Valid;

@RestController
@RequestMapping("api/customer")
public class ConsumerController {
	private final ConsumerRepository consumerRepository;
	private final ConsumerRepresentationModelAssembler assembler;

	@Autowired
	public ConsumerController(ConsumerRepository consumerRepository,
														ConsumerRepresentationModelAssembler assembler) {
		this.consumerRepository = consumerRepository;
		this.assembler = assembler;
	}

	@GetMapping
	public ResponseEntity<?> index() {
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathVariable("id") Long id) {
		return ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<?> createOne(@Valid @RequestBody ConsumerDTO consumerDTO) {
		Consumer aConsumer = consumerRepository.save(consumerDTO.toModel());
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(assembler.toModel(aConsumer));
	}
}
