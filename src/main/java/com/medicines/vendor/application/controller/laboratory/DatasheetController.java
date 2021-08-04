package com.medicines.vendor.application.controller.laboratory;

import com.medicines.vendor.domain.medicine.Datasheet;
import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.dto.DatasheetDTO;
import com.medicines.vendor.domain.medicine.services.DatasheetService;
import com.medicines.vendor.domain.medicine.services.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/laboratory/{lab_id}/datasheet")
public class DatasheetController {
	private final DatasheetService datasheetService;
	private final MedicineService medicineService;

	@Autowired
	public DatasheetController(DatasheetService datasheetService, MedicineService medicineService) {
		this.datasheetService = datasheetService;
		this.medicineService = medicineService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@PreAuthorize("hasAnyRole('ROLE_LAB_ADMIN', 'ROLE_TECHNICAL')")
	public Datasheet createDatasheet(@RequestBody @Valid DatasheetDTO datasheetDTO) {
		Medicine medicine = medicineService.getMedicineByCode(datasheetDTO.getMedicineCode());
		return datasheetService.createDatasheetForMedicine(datasheetDTO, medicine);
	}
}
