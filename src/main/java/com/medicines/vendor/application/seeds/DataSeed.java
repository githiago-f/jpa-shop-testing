package com.medicines.vendor.application.seeds;

import com.medicines.vendor.application.security.ApplicationUserDetails;
import com.medicines.vendor.domain.laboratory.Laboratory;
import com.medicines.vendor.domain.laboratory.repository.LaboratoryRepository;
import com.medicines.vendor.domain.medicine.Datasheet;
import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.repository.MedicinesRepository;
import com.medicines.vendor.domain.medicine.vo.MedicineState;
import com.medicines.vendor.domain.users.repository.UserRepository;
import com.medicines.vendor.domain.users.vo.EmployeeData;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.medicines.vendor.application.security.ApplicationRole.CONSUMER;
import static com.medicines.vendor.application.security.ApplicationRole.LAB_ADMIN;

@Configuration
public class DataSeed {
	private Medicine medicineFactory(String code, String name, String price, Laboratory laboratory) {
		return Medicine.builder()
			.code(code)
			.name(name)
			.state(MedicineState.DATASHEET_REQUIRED)
			.price(new BigDecimal(price))
			.laboratory(laboratory)
			.build();
	}

	@Bean
	CommandLineRunner applicationDataSeed(
		MedicinesRepository medicinesRepository, LaboratoryRepository laboratoryRepository,
		PasswordEncoder passwordEncoder, UserRepository userRepository) {
		return args -> {
			Laboratory laboratory = Laboratory.builder()
				.name("Lab 1")
				.cnpj("84.913.305/0001-84")
				.employees(new ArrayList<>())
				.build();

			EmployeeData employeeData = EmployeeData.builder()
				.cpf("473.348.580-85")
				.password(passwordEncoder.encode("pass123"))
				.username("admin@email.com")
				.fullName("ADMIN")
				.role(LAB_ADMIN)
				.build();

			laboratory.createLabAdmin(employeeData);
			laboratoryRepository.save(laboratory);

			Medicine medicine = medicineFactory("11571-0", "Medicine 1", "41.0", laboratory);
			Medicine medicine1 = medicineFactory("11571-1", "Medicine 2", "57.0", laboratory);
			Medicine medicine2 = medicineFactory("11571-2", "Medicine 3", "57.0", laboratory);
			Medicine medicine3 = medicineFactory("11571-3", "Medicine 4", "57.0", laboratory);
			Medicine medicine4 = medicineFactory("11571-4", "Medicine 5", "57.0", laboratory);
			Medicine medicine5 = medicineFactory("11571-5", "Medicine 6", "57.0", laboratory);
			Medicine medicine6 = medicineFactory("11571-6", "Medicine 7", "57.0", laboratory);

			Datasheet.DatasheetBuilder datasheet = Datasheet.builder()
				.indication("Medicine indicated to blah")
				.activeIngredient("sugar");

			medicine.setDatasheet(datasheet.medicine(medicine).build());
			medicine.enable();
			medicine1.setDatasheet(datasheet.medicine(medicine1).build());
			medicine1.enable();

			List<Medicine> medicines = new ArrayList<>(
				Arrays.asList(
					medicine,
					medicine1,
					medicine2,
					medicine3,
					medicine4,
					medicine5,
					medicine6
				)
			);

			/// Generated CPF
			ApplicationUserDetails consumer = new ApplicationUserDetails(
				"user@email.com",
				passwordEncoder.encode("pass123"),
				CONSUMER,
				"Test User",
				"119.886.000-65"
			);
			userRepository.save(consumer);

			medicinesRepository.saveAll(medicines);
		};
	}

}
