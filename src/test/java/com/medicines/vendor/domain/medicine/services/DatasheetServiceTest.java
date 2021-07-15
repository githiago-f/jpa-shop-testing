package com.medicines.vendor.domain.medicine.services;

import com.medicines.vendor.domain.medicine.Datasheet;
import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.errors.CannotCreateDatasheet;
import com.medicines.vendor.domain.medicine.repository.MedicinesRepository;
import com.medicines.vendor.domain.medicine.vo.MedicineState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("# DatasheetService")
@SpringBootTest
class DatasheetServiceTest {
	@Autowired
	protected MedicinesRepository repository;
	@Autowired
	protected DatasheetService datasheetService;

	protected Medicine medicineWithState(MedicineState state) {
		return Medicine.builder()
			.code("code-1")
			.name("medicine")
			.price(new BigDecimal("14.00"))
			.state(state)
			.build();
	}

	@Nested
	@DisplayName("Medicine is waiting datasheet")
	public class MedicineWaitingDatasheet {
		private Medicine medicine;
		@BeforeEach
		void setUp() {
			medicine = medicineWithState(MedicineState.DATASHEET_REQUIRED);
			repository.save(medicine);
		}

		@Test
		@DisplayName("it can generate a datasheet")
		void itCanGenerateADatasheet() {
			Datasheet datasheet = datasheetService.createDatasheetForMedicine(medicine);
			assertEquals(datasheet.medicine.getCode(), medicine.getCode());
		}
	}

	@Nested
	@DisplayName("Medicine is not waiting datasheet")
	public class MedicineNotWaitingDatasheet {
		private Medicine medicine;
		@BeforeEach
		void setUp() {
			medicine = medicineWithState(MedicineState.ACTIVE);
			repository.save(medicine);
		}

		@Test
		@DisplayName("- it throws illegal operation")
		void itThrowsIllegalOperation() {
			Executable canHaveError = () -> datasheetService.createDatasheetForMedicine(medicine);
			assertThrows(CannotCreateDatasheet.class, canHaveError);
		}
	}
}
