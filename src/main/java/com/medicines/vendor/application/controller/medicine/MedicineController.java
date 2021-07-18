package com.medicines.vendor.application.controller.medicine;

import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.dto.MedicineDTO;
import com.medicines.vendor.domain.medicine.services.MedicineService;
import com.medicines.vendor.domain.medicine.dto.MedicineListable;
import com.medicines.vendor.domain.medicine.resource_mappers.MedicineListableAssembler;
import com.medicines.vendor.domain.medicine.resource_mappers.MedicineModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/medicines")
public class MedicineController {
	private final MedicineService medicineService;
	private final MedicineModelAssembler assembler;
	private final MedicineListableAssembler listableAssembler;

	@Autowired
	public MedicineController(
		MedicineService medicineService, MedicineModelAssembler assembler,
		MedicineListableAssembler listableAssembler) {
		this.medicineService = medicineService;
		this.assembler = assembler;
		this.listableAssembler = listableAssembler;
	}

	@GetMapping
	public CollectionModel<?> getMedicines(
		@PageableDefault(size = 15, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
		Page<MedicineListable> medicinePage = medicineService.getAllActiveMedicines(pageable);
		return listableAssembler.toCollectionModel(medicinePage);
	}

	@GetMapping("/{code}")
	public EntityModel<Medicine> getMedicineByCode(@PathVariable("code") String code) {
		return assembler.toModel(
			medicineService.getMedicineByCode(code)
		);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntityModel<Medicine> createOne(@RequestBody @Valid MedicineDTO medicineDTO) {
		Medicine medicine = medicineService.saveMedicine(medicineDTO.toCreateEntity());
		return assembler.toModel(medicine);
	}
}
