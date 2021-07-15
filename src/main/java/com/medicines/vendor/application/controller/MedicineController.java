package com.medicines.vendor.application.controller;

import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.dto.MedicineDTO;
import com.medicines.vendor.domain.medicine.services.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/medicines")
public class MedicineController {
	private final MedicineService medicineService;

	@Autowired
	public MedicineController(MedicineService medicineService) {
		this.medicineService = medicineService;
	}

	@GetMapping
	public CollectionModel<?> getMedicines(
		@PageableDefault(size = 15, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
		return medicineService.getOnlyActiveMedicines(pageable);
	}

	@GetMapping("/{code}")
	public ResponseEntity<?> getMedicineByCode(@PathVariable("code") String code) {
		return medicineService.getMedicineByCode(code)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntityModel<Medicine> createOne(@RequestBody @Valid MedicineDTO medicineDTO) {
		return medicineService.saveMedicine(medicineDTO.toCreateEntity());
	}
}
