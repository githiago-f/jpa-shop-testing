package com.medicines.vendor.domain.medicine.services;

import com.medicines.vendor.domain.medicine.Datasheet;
import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.dto.DatasheetDTO;
import com.medicines.vendor.domain.medicine.errors.CannotCreateDatasheet;
import com.medicines.vendor.domain.medicine.repository.MedicinesRepository;
import com.medicines.vendor.domain.medicine.vo.MedicineState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatasheetService {
	private final MedicinesRepository medicinesRepository;

	@Autowired
	public DatasheetService(MedicinesRepository medicinesRepository) {
		this.medicinesRepository = medicinesRepository;
	}

	public Datasheet createDatasheetForMedicine(DatasheetDTO datasheetDTO) {
		Medicine medicine = medicinesRepository.getById(datasheetDTO.getMedicineCode());
		if (!medicine.getState().equals(MedicineState.DATASHEET_REQUIRED)) {
			throw new CannotCreateDatasheet();
		}

		medicine.setState(MedicineState.ACTIVE);

		return datasheetDTO.toEntity()
			.medicine(medicine)
			.build();
	}
}
