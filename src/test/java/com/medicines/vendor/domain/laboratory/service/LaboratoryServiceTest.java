package com.medicines.vendor.domain.laboratory.service;

import com.medicines.vendor.domain.laboratory.Laboratory;
import com.medicines.vendor.domain.laboratory.repository.LaboratoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Laboratory service test")
class LaboratoryServiceTest {
	UUID exampleId = UUID.randomUUID();
	Laboratory laboratory;

	@Mock LaboratoryRepository laboratoryRepository;
	@InjectMocks LaboratoryService laboratoryService;

	@BeforeEach
	void setUp() {
		laboratory = Laboratory.builder()
			.id(exampleId)
			.name("Test lab")
			.cnpj("03.147.313/0001-65")
			.employees(new ArrayList<>())
			.build();

		when(laboratoryRepository.findById(exampleId))
			.thenReturn(Optional.of(laboratory));
	}
}
