package com.medicines.vendor.domain.medicine.services;

import com.medicines.vendor.domain.medicine.Datasheet;
import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.dto.DatasheetDTO;
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
	protected MedicinesRepository repository;
	protected DatasheetService datasheetService = new DatasheetService(repository);

	protected String medicineCode = "code-1";

	protected Medicine medicineWithState(MedicineState state) {
		return Medicine.builder()
			.code(medicineCode)
			.name("medicine")
			.price(new BigDecimal("14.00"))
			.state(state)
			.build();
	}
	protected DatasheetDTO candyDatasheet() {
		return DatasheetDTO.builder()
			.medicineCode(medicineCode)
			.activeIngredient("sugar")
			.indication("any")
			.build();
	}

	@Nested
	@DisplayName("Medicine is waiting datasheet")
	public class MedicineWaitingDatasheet {
		DatasheetDTO dto;

		@BeforeEach
		void setUp() {
			Medicine medicine = medicineWithState(MedicineState.DATASHEET_REQUIRED);
			repository.save(medicine);
			dto = candyDatasheet();
		}

		@Test
		@DisplayName("- it's result is a valid datasheet")
		void itCanGenerateADatasheet() {
			Datasheet datasheet = datasheetService.createDatasheetForMedicine(dto);
			assertEquals(medicineCode, datasheet.getMedicine().getCode());
			assertEquals("sugar", datasheet.getActiveIngredient());
		}

		@Test
		@DisplayName("- medicine state should be activated")
		void medicineStateShouldBeActive() {
			Datasheet datasheet = datasheetService.createDatasheetForMedicine(dto);
			assertEquals(MedicineState.ACTIVE, datasheet.getMedicine().getState());
		}
	}

	@Nested
	@DisplayName("Medicine is not waiting datasheet")
	public class MedicineNotWaitingDatasheet {
		DatasheetDTO dto;

		@BeforeEach
		void setUp() {
			Medicine medicine = medicineWithState(MedicineState.ACTIVE);
			repository.save(medicine);
			dto = candyDatasheet();
		}

		@Test
		@DisplayName("- it throws illegal operation")
		void itThrowsIllegalOperation() {
			Executable canHaveError = () -> datasheetService.createDatasheetForMedicine(dto);
			assertThrows(CannotCreateDatasheet.class, canHaveError);
		}
	}
}
