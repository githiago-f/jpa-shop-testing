package com.medicines.vendor.domain.medicine.services;

import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.repository.MedicinesRepository;
import com.medicines.vendor.domain.medicine.vo.MedicineState;
import com.medicines.vendor.shared.errors.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class MedicineService {
	private final MedicinesRepository repository;

	@Autowired
	public MedicineService(MedicinesRepository repository) {
		this.repository = repository;
	}

	public Medicine saveMedicine(Medicine medicine) {
		return repository.save(medicine);
	}

	public Page<Medicine> getAllActiveMedicines(Pageable pageable) {
		Medicine medicineOnlyDatasheet = Medicine.builder()
			.state(MedicineState.ACTIVE)
			.build();
		Example<Medicine> example = Example.of(medicineOnlyDatasheet);
		return repository.findAll(example, pageable);
	}

	public Medicine getMedicineByCode(String code) {
		return repository.findByCode(code)
			.orElseThrow(() -> new NotFoundException("Could not find"));
	}
}
