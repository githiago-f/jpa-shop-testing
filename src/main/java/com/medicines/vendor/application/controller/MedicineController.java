package com.medicines.vendor.application.controller;

import com.medicines.vendor.domain.medicine.Datasheet;
import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.dto.DatasheetDTO;
import com.medicines.vendor.domain.medicine.dto.MedicineDTO;
import com.medicines.vendor.domain.medicine.services.DatasheetService;
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
	private final DatasheetService datasheetService;

	@Autowired
	public MedicineController(MedicineService medicineService, DatasheetService datasheetService) {
		this.medicineService = medicineService;
		this.datasheetService = datasheetService;
	}

	@GetMapping
	public CollectionModel<?> getMedicines(
		@PageableDefault(size = 15, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
		return medicineService.getOnlyActiveMedicineResources(pageable);
	}

	@GetMapping("/{code}")
	public ResponseEntity<?> getMedicineByCode(@PathVariable("code") String code) {
		return medicineService.getMedicineResourceByCode(code)
			.map(ResponseEntity::ok)
			.orElse(ResponseEntity.notFound().build());
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntityModel<Medicine> createOne(@RequestBody @Valid MedicineDTO medicineDTO) {
		return medicineService.saveMedicine(medicineDTO.toCreateEntity());
	}

	@PostMapping("/{code}/datasheet")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Datasheet createDatasheet(@RequestBody @Valid DatasheetDTO datasheetDTO,
																	 @PathVariable("code") String code) {
		datasheetDTO.setMedicineCode(code);
		return datasheetService.createDatasheetForMedicine(datasheetDTO);
	}
}
