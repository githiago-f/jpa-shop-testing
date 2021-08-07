package com.medicines.vendor.application.controller.medicine;

import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.services.MedicineService;
import com.medicines.vendor.domain.medicine.dto.MedicineModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/medicines")
public class MedicineController {
	private final MedicineService medicineService;

	@Autowired
	public MedicineController(MedicineService medicineService) {
		this.medicineService = medicineService;
	}

	@GetMapping
	public Page<MedicineModel> getMedicines(
		@PageableDefault(size = 15, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
		return medicineService.getAllActiveMedicines(pageable);
	}

	@GetMapping("/{code}")
	public Medicine getMedicineByCode(@PathVariable("code") String code) {
		return medicineService.getMedicineByCode(code);
	}
}
