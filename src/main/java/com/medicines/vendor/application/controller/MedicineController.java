package com.medicines.vendor.application.controller;

import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.dto.MedicineDTO;
import com.medicines.vendor.domain.medicine.repository.MedicinesRepository;
import com.medicines.vendor.domain.resource_models.MedicineRepresentationModelAssembler;
import com.medicines.vendor.shared.errors.HandleUniqueFieldViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/medicines")
public class MedicineController {
	private final MedicinesRepository medicinesRepository;
	private final MedicineRepresentationModelAssembler assembler;
	private final HandleUniqueFieldViolation uniqueViolationHandler;

	@Autowired
	public MedicineController(MedicinesRepository medicinesRepository,
														MedicineRepresentationModelAssembler assembler, HandleUniqueFieldViolation uniqueViolationHandler) {
		this.medicinesRepository = medicinesRepository;
		this.assembler = assembler;
		this.uniqueViolationHandler = uniqueViolationHandler;
	}

	@GetMapping
	public CollectionModel<?> getMedicines(
		@PageableDefault(size = 15, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
		return assembler.toCollectionModel(medicinesRepository.findAll(pageable));
	}

	@GetMapping("/{code}")
	public ResponseEntity<?> getMedicineByCode(@PathVariable("code") String code) {
		return medicinesRepository.findById(code)
			.map(assembler::toModel)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	public ResponseEntity<?> createOne(@RequestBody() @Valid MedicineDTO medicineDTO, Errors errors)
	{
		Medicine medicine = medicinesRepository.save(medicineDTO.toEntity());
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(assembler.toModel(medicine));
	}
}
