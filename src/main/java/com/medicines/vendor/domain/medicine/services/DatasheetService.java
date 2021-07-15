package com.medicines.vendor.domain.medicine.services;

import com.medicines.vendor.domain.medicine.Datasheet;
import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.errors.CannotCreateDatasheet;
import com.medicines.vendor.domain.medicine.vo.MedicineState;
import org.springframework.stereotype.Service;

@Service
public class DatasheetService {
	Datasheet createDatasheetForMedicine(Medicine medicine) {
		if (!medicine.getState().equals(MedicineState.DATASHEET_REQUIRED)) {
			throw new CannotCreateDatasheet();
		}
		return null;
	}
}
