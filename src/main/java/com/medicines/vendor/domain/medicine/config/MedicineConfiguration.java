package com.medicines.vendor.domain.medicine.config;

import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.repository.MedicinesRepository;
import com.medicines.vendor.domain.medicine.vo.MedicineState;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class MedicineConfiguration {
	private Medicine medicineFactory(String code, String name, String price) {
		return Medicine.builder()
			.code(code)
			.name(name)
			.state(MedicineState.DATASHEET_REQUIRED)
			.price(new BigDecimal(price))
			.build();
	}

	@Bean
	CommandLineRunner commandLineRunner(MedicinesRepository medicinesRepository) {
		return args -> {
			Medicine medicine = medicineFactory("11571-0", "Medicine 1", "41.0");
			Medicine medicine1 = medicineFactory("11571-1", "Medicine 2", "57.0");
			Medicine medicine2 = medicineFactory("11571-2", "Medicine 3", "57.0");
			Medicine medicine3 = medicineFactory("11571-3", "Medicine 4", "57.0");
			Medicine medicine4 = medicineFactory("11571-4", "Medicine 5", "57.0");
			Medicine medicine5 = medicineFactory("11571-5", "Medicine 6", "57.0");
			Medicine medicine6 = medicineFactory("11571-6", "Medicine 7", "57.0");
			medicinesRepository.saveAll(
				List.of(medicine, medicine1, medicine2, medicine3, medicine4, medicine5, medicine6)
			);
		};
	}

}
