package com.medicines.vendor.domain.medicine.services;

import com.medicines.vendor.domain.medicine.Datasheet;
import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.dto.DatasheetDTO;
import com.medicines.vendor.domain.medicine.errors.CannotCreateDatasheet;
import com.medicines.vendor.domain.medicine.repository.DatasheetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DatasheetService {
	private final DatasheetRepository datasheetRepository;
	private final MedicineService medicineService;

	@Autowired
	public DatasheetService(
		DatasheetRepository datasheetRepository,
		MedicineService medicineService) {
		this.datasheetRepository = datasheetRepository;
		this.medicineService = medicineService;
	}

	public Datasheet createDatasheetForMedicine(DatasheetDTO datasheetDTO) {
		String code = datasheetDTO.getMedicineCode();
		Medicine medicine = medicineService.getMedicineByCode(code);

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
