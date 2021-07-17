package com.medicines.vendor.application.controller.medicine;

import com.medicines.vendor.domain.medicine.Datasheet;
import com.medicines.vendor.domain.medicine.dto.DatasheetDTO;
import com.medicines.vendor.domain.medicine.services.DatasheetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/medicines/{code}/datasheet")
public class DatasheetController {
	private final DatasheetService datasheetService;

	@Autowired
	public DatasheetController(DatasheetService datasheetService) {
		this.datasheetService = datasheetService;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Datasheet createDatasheet(@RequestBody @Valid DatasheetDTO datasheetDTO,
																	 @PathVariable("code") String code) {
		datasheetDTO.setMedicineCode(code);
		return datasheetService.createDatasheetForMedicine(datasheetDTO);
	}
}
