package com.medicines.vendor.domain.medicine.services;

import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.dto.MedicineListable;
import com.medicines.vendor.domain.medicine.repository.MedicinesRepository;
import com.medicines.vendor.domain.medicine.vo.MedicineState;
import com.medicines.vendor.domain.shared.errors.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
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

	public Page<MedicineListable> getAllActiveMedicines(Pageable pageable) {
		return repository.findAllActiveListableMedicines(MedicineState.ACTIVE, pageable);
	}

	public Medicine getMedicineByCode(String code) {
		return repository.findByCode(code)
			.orElseThrow(() -> new NotFoundException("Could not find"));
	}

	public Page<MedicineListable> getAllMedicines(Pageable pageable) {
		return repository.findAllListableMedicines(pageable);
	}
}
