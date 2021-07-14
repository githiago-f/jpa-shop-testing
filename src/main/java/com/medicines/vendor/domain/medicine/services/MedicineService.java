package com.medicines.vendor.domain.medicine.services;

import com.medicines.vendor.domain.medicine.Medicine;
import com.medicines.vendor.domain.medicine.repository.MedicinesRepository;
import com.medicines.vendor.domain.resource_models.MedicineModelAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MedicineService {
	private final MedicinesRepository repository;
	private final MedicineModelAssembler assembler;

	@Autowired
	public MedicineService(MedicinesRepository repository, MedicineModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	public EntityModel<Medicine> saveMedicine(Medicine medicine) {
		return assembler.toModel(repository.save(medicine));
	}

	public CollectionModel<EntityModel<Medicine>> pageableListOfMedicines(Pageable pageable) {
		return assembler.toCollectionModel(repository.findAll(pageable));
	}

	public Optional<?> getMedicineByCode(String code) {
		return repository.findById(code).map(assembler::toModel);
	}
}
