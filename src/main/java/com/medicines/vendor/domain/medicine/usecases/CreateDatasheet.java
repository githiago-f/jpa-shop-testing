package com.medicines.vendor.domain.medicine.usecases;

import com.medicines.vendor.domain.medicine.Datasheet;
import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.order.vo.MedicineState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateDatasheet {
	@Autowired
	public CreateDatasheet() {}

	void execute(Medicine medicine) {
		if(!medicine.getState().equals(MedicineState.DATASHEET)){
			return;
		}
		new Datasheet(medicine);
	}
}
