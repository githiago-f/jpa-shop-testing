package com.medicines.vendor.application.controller.laboratory;

import static org.springframework.data.domain.Sort.Direction.DESC;

import com.medicines.vendor.domain.laboratory.Laboratory;
import com.medicines.vendor.domain.laboratory.service.LaboratoryService;
import com.medicines.vendor.domain.laboratory.validators.IsEmployeeOf;
import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.dto.*;
import com.medicines.vendor.domain.medicine.repository.MedicineSpecification;
import com.medicines.vendor.domain.medicine.services.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@Validated
@RestController
@RequestMapping(value = "/api/v1/laboratory/{lab_id}/medicines")
public class LaboratoryMedicineController {
	private final LaboratoryService laboratoryService;
	private final MedicineService medicineService;

	@Autowired
	public LaboratoryMedicineController(LaboratoryService laboratoryService,
																			MedicineService medicineService) {
		this.laboratoryService = laboratoryService;
		this.medicineService = medicineService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAuthority('medicine:write')")
	public Medicine createOne(@PathVariable("lab_id") @IsEmployeeOf UUID labId,
																				 @RequestBody @Valid MedicineDTO medicineDTO) {
		Laboratory laboratory = laboratoryService.findLaboratory(labId);
		return medicineService.saveMedicine(
			medicineDTO.toCreateEntity(laboratory)
		);
	}

	@GetMapping
	@PreAuthorize("hasAuthority('medicine:read_all')")
	public Page<MedicineModel> getMedicines(
		@PathVariable("lab_id") @IsEmployeeOf UUID uuid,
		@PageableDefault(sort = "createdAt", direction = DESC) Pageable pageable) {
		if(pageable.getPageSize() > 20) {
			throw new RuntimeException("Cannot create a page with more than 20 items");
		}
		return this.medicineService.getAllMedicines(
			MedicineSpecification.isFromLaboratory(uuid),
			pageable
		);
	}

	@GetMapping("/{code}")
	public Medicine getMedicine(@PathVariable("code") String code) {
		return this.medicineService.getMedicineByCode(code);
	}

	@PutMapping("/{code}")
	@PreAuthorize("hasAnyAuthority('medicine:write', 'medicine:read_all')")
	public Medicine updateMedicine(@PathVariable("code") String code) {
		return null;
	}
}
