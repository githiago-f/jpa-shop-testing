package com.medicines.vendor.domain.medicine.services;

import com.medicines.vendor.domain.medicine.Datasheet;
import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.dto.DatasheetDTO;
import com.medicines.vendor.domain.medicine.errors.CannotCreateDatasheet;
import com.medicines.vendor.domain.medicine.repository.DatasheetRepository;
import com.medicines.vendor.domain.medicine.repository.MedicinesRepository;
import com.medicines.vendor.domain.medicine.vo.MedicineState;
import com.medicines.vendor.shared.errors.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatasheetService {
	private final MedicinesRepository medicinesRepository;
	private final DatasheetRepository datasheetRepository;

	@Autowired
	public DatasheetService(DatasheetRepository datasheetRepository, MedicinesRepository medicinesRepository) {
		this.medicinesRepository = medicinesRepository;
		this.datasheetRepository = datasheetRepository;
	}

	public Datasheet createDatasheetForMedicine(DatasheetDTO datasheetDTO) {
		Optional<Medicine> loadMedicine = medicinesRepository.findByCode(
			datasheetDTO.getMedicineCode()
		);

		if(loadMedicine.isEmpty()) {
			throw new NotFoundException("Medicine does not exist");
		}
		Medicine medicine = loadMedicine.get();

		if (!medicine.isWaitingDatasheet()) {
			throw new CannotCreateDatasheet();
		}

		medicine.enable();
		Datasheet datasheet = datasheetDTO.toEntity()
			.medicine(medicine)
			.build();

		return datasheetRepository.save(datasheet);
	}
}
