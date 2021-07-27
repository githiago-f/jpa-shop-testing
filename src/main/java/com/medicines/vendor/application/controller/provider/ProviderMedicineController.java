package com.medicines.vendor.application.controller.provider;

import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.dto.MedicineDTO;
import com.medicines.vendor.domain.medicine.dto.MedicineListable;
import com.medicines.vendor.application.hateoas.medicine.MedicineModelAssembler;
import com.medicines.vendor.domain.medicine.services.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/providers/{providerId}/medicines")
public class ProviderMedicineController {
	private final MedicineService medicineService;
	private final MedicineModelAssembler assembler;
//	private final MedicineListableAssembler listableAssembler;

	@Autowired
	public ProviderMedicineController(
		MedicineService medicineService,
		MedicineModelAssembler assembler
//		MedicineListableAssembler listableAssembler
	) {
		this.medicineService = medicineService;
		this.assembler = assembler;
//		this.listableAssembler = listableAssembler;
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntityModel<Medicine> createOne(@RequestBody @Valid MedicineDTO medicineDTO) {
		Medicine medicine = medicineService.saveMedicine(medicineDTO.toCreateEntity());
		return assembler.toModel(medicine);
	}

	@GetMapping
	public List<MedicineListable> getMedicines() {
		return List.of();
	}

	@PutMapping("/{code}")
	public Medicine updateMedicine(@PathVariable("code") String code) {
		return null;
	}
}
