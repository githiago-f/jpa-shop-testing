package com.medicines.vendor.domain.laboratory.service;

import com.medicines.vendor.domain.laboratory.Laboratory;
import com.medicines.vendor.domain.laboratory.repository.LaboratoryRepository;
import com.medicines.vendor.domain.shared.errors.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LaboratoryService {
	private final LaboratoryRepository laboratoryRepository;

	@Autowired
	public LaboratoryService(LaboratoryRepository laboratoryRepository) {
		this.laboratoryRepository = laboratoryRepository;
	}

	public Laboratory findLaboratory(UUID laboratoryId) {
		return laboratoryRepository.findById(laboratoryId)
			.orElseThrow(() -> new NotFoundException("Laboratory not found!"));
	}

	public Page<Laboratory> listLaboratories(Pageable pageable) {
		return laboratoryRepository.findAll(pageable);
	}
}
