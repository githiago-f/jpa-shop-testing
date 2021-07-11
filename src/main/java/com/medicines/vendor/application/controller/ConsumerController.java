package com.medicines.vendor.application.controller;

import com.medicines.vendor.domain.users.Consumer;
import com.medicines.vendor.domain.users.dto.ConsumerDTO;
import com.medicines.vendor.domain.users.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.net.URI;


@RestController
@RequestMapping("api/customer")
@Validated
public class ConsumerController {
	private final ConsumerRepository consumerRepository;

	@Autowired
	public ConsumerController(ConsumerRepository consumerRepository) {
		this.consumerRepository = consumerRepository;
	}

	@GetMapping
	public ResponseEntity<?> index() {
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findOne(@PathParam("id") Long id) {
		return ResponseEntity.noContent().build();
	}

	@PostMapping
	public ResponseEntity<?> createOne(@Valid @RequestBody ConsumerDTO consumerDTO) {
		Consumer savedObject = consumerRepository.save(consumerDTO.toModel());
		URI uri = URI.create("/api/customer/" + savedObject.getId());
		return ResponseEntity.created(uri).build();
	}
}
