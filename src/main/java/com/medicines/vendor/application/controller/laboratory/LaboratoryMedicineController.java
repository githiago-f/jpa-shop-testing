package com.medicines.vendor.application.controller.laboratory;

import static org.springframework.data.domain.Sort.Direction.DESC;

import com.medicines.vendor.application.hateoas.laboratory.LaboratoryMedicineAssembler;
import com.medicines.vendor.application.hateoas.medicine.MedicineModelAssembler;
import com.medicines.vendor.domain.laboratory.Laboratory;
import com.medicines.vendor.domain.laboratory.service.LaboratoryService;
import com.medicines.vendor.domain.laboratory.validators.IsEmployeeOf;
import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.dto.*;
import com.medicines.vendor.domain.medicine.services.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.hateoas.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/laboratory/{lab_id}/medicines")
public class LaboratoryMedicineController {
	private final LaboratoryService laboratoryService;
	private final MedicineService medicineService;
	private final MedicineModelAssembler assembler;
	private final LaboratoryMedicineAssembler labMedicineAssembler;

	@Autowired
	public LaboratoryMedicineController(
		LaboratoryService laboratoryService,
		MedicineService medicineService,
		MedicineModelAssembler assembler,
		LaboratoryMedicineAssembler labMedicineAssembler) {
		this.laboratoryService = laboratoryService;
		this.medicineService = medicineService;
		this.assembler = assembler;
		this.labMedicineAssembler = labMedicineAssembler;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('medicine:write')")
	public EntityModel<Medicine> createOne(@IsEmployeeOf @PathVariable("lab_id") Long labId,
																				 @RequestBody @Valid MedicineDTO medicineDTO) {
		Laboratory laboratory = laboratoryService.findLaboratory(labId);
		Medicine medicine = medicineService.saveMedicine(
			medicineDTO.toCreateEntity(laboratory)
		);
		return assembler.toModel(medicine);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('medicine:read_all')")
	public CollectionModel<EntityModel<MedicineListable>> getMedicines(
		@PageableDefault(size = 15, sort = "createdAt", direction = DESC) Pageable pageable) {
		Page<MedicineListable> allMedicines = this.medicineService.getAllMedicines(pageable);
		return labMedicineAssembler.toCollectionModel(allMedicines);
	}

	@GetMapping("/{code}")
	public EntityModel<Medicine> getMedicine(@PathVariable("code") String code) {
		Medicine medicine = this.medicineService.getMedicineByCode(code);
		return assembler.toModel(medicine);
	}

	@PutMapping("/{code}")
	@PreAuthorize("hasAnyAuthority('medicine:read_all')")
	public Medicine updateMedicine(@PathVariable("code") String code) {
		return null;
	}
}
