package com.medicines.vendor.application.controller;

import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.dto.MedicineDTO;
import com.medicines.vendor.domain.medicine.repository.MedicinesRepository;
import com.medicines.vendor.domain.resource_models.MedicineRepresentationModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping(value = "/api/medicines")
public class MedicineController {
	private final MedicinesRepository medicinesRepository;
	private final MedicineRepresentationModelAssembler assembler;

	@Autowired
	public MedicineController(MedicinesRepository medicinesRepository,
														MedicineRepresentationModelAssembler assembler) {
		this.medicinesRepository = medicinesRepository;
		this.assembler = assembler;
	}

	@GetMapping("")
	public CollectionModel<?> getMedicines(
		@RequestParam(value = "page", required = false, defaultValue = "1") Integer page) {
		if(page==0){page=1;}
		Pageable pageable = Pageable.ofSize(15).withPage(page-1);
		return assembler.toCollectionModel(
			medicinesRepository.findAll(pageable).getContent()
		);
	}

	@GetMapping("/{code}")
	public ResponseEntity<?> getMedicineByCode(@PathVariable("id") String code) {
		return medicinesRepository.findByCode(code)
			.map(assembler::toModel)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping("")
	public ResponseEntity<?> createOne(@RequestBody() MedicineDTO medicineDTO) {
		Medicine medicine = medicinesRepository.save(medicineDTO.toEntity());
		URI uri = URI.create("/" + medicine.getCode());
		return ResponseEntity.created(uri).build();
	}
}
