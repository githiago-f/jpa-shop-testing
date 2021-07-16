package com.medicines.vendor.domain.medicine.services;

import com.medicines.vendor.domain.medicine.Datasheet;
import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.dto.DatasheetDTO;
import com.medicines.vendor.domain.medicine.errors.CannotCreateDatasheet;
import com.medicines.vendor.domain.medicine.repository.DatasheetRepository;
import com.medicines.vendor.domain.medicine.repository.MedicinesRepository;
import com.medicines.vendor.domain.medicine.vo.MedicineState;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@DisplayName("# DatasheetService")
class DatasheetServiceTest {
	@Mock protected MedicinesRepository medicinesRepository;
	@Mock protected DatasheetRepository datasheetRepository;
	@InjectMocks protected DatasheetService datasheetService;
	protected String medicineCode = "code-1";

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
		Medicine medicine;

		@BeforeEach
		public void setUp() {
			medicine = Medicine.builder()
				.code(medicineCode)
				.state(MedicineState.DATASHEET_REQUIRED)
				.build();
			dto = candyDatasheet();
			Mockito.when(medicinesRepository.findByCode(medicineCode))
				.thenReturn(Optional.ofNullable(medicine));
			Mockito.when(datasheetRepository.save(any(Datasheet.class)))
				.thenReturn(dto.toEntity().medicine(medicine).build());
		}

		@Test
		@DisplayName("- it's result is a valid datasheet")
		public void itCanGenerateADatasheet() {
			Datasheet datasheet = datasheetService.createDatasheetForMedicine(dto);
			assertEquals(medicineCode, datasheet.getMedicine().getCode());
			assertEquals("sugar", datasheet.getActiveIngredient());
		}

		@Test
		@DisplayName("- medicine state should be activated")
		public void medicineStateShouldBeActive() {
			Datasheet datasheet = datasheetService.createDatasheetForMedicine(dto);
			assertEquals(MedicineState.ACTIVE, datasheet.getMedicine().getState());
		}
	}

	@Nested
	@DisplayName("Medicine is not waiting datasheet")
	public class MedicineNotWaitingDatasheet {
		DatasheetDTO dto;
		private Medicine medicine;

		@BeforeEach
		void setUp() {
			dto = candyDatasheet();
			medicine = Medicine.builder()
				.code(medicineCode)
				.state(MedicineState.ACTIVE)
				.build();
		}

		@Test
		@DisplayName("- it is a unprocessable entity status")
		void itThrowsIllegalOperation() {
			Mockito.when(medicinesRepository.findByCode(medicineCode))
				.thenReturn(Optional.ofNullable(medicine));
			Executable canHaveError = () -> datasheetService.createDatasheetForMedicine(dto);
			assertThrows(CannotCreateDatasheet.class, canHaveError);
		}
	}
}
