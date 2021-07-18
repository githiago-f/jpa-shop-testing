package com.medicines.vendor.domain.medicine.services;

import com.medicines.vendor.domain.medicine.Datasheet;
import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.dto.DatasheetDTO;
import com.medicines.vendor.domain.medicine.errors.CannotCreateDatasheet;
import com.medicines.vendor.domain.medicine.repository.DatasheetRepository;
import com.medicines.vendor.domain.medicine.vo.MedicineState;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
@DisplayName("# DatasheetService")
class DatasheetServiceTest {
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
			Mockito.when(datasheetRepository.save(any(Datasheet.class)))
				.thenReturn(dto.toEntity().medicine(medicine).build());
		}

		@Test
		@DisplayName("- it's result is a valid datasheet")
		public void itCanGenerateADatasheet() {
			Datasheet datasheet = datasheetService.createDatasheetForMedicine(dto, medicine);
			assertEquals(medicineCode, datasheet.getMedicine().getCode());
			assertEquals("sugar", datasheet.getActiveIngredient());
		}

		@Test
		@DisplayName("- medicine state should be activated")
		public void medicineStateShouldBeActive() {
			Datasheet datasheet = datasheetService.createDatasheetForMedicine(dto, medicine);
			assertEquals(MedicineState.ACTIVE, datasheet.getMedicine().getState());
			assertEquals(MedicineState.ACTIVE, medicine.getState());
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
		@DisplayName("- cannot alter medicine or process the request")
		void itThrowsIllegalOperation() {
			Executable canHaveError = () -> datasheetService.createDatasheetForMedicine(dto, medicine);
			assertThrows(CannotCreateDatasheet.class, canHaveError);
			assertEquals(MedicineState.ACTIVE, medicine.getState());
		}
	}
}
