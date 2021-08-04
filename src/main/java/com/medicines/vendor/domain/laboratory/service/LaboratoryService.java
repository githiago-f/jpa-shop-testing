package com.medicines.vendor.domain.laboratory.service;

import com.medicines.vendor.domain.laboratory.Laboratory;
import com.medicines.vendor.domain.laboratory.repository.LaboratoryRepository;
import com.medicines.vendor.domain.shared.errors.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LaboratoryService {
	private final LaboratoryRepository laboratoryRepository;

	@Autowired
	public LaboratoryService(LaboratoryRepository laboratoryRepository) {
		this.laboratoryRepository = laboratoryRepository;
	}

	public Laboratory findLaboratory(Long laboratoryId) {
		return laboratoryRepository.findById(laboratoryId)
			.orElseThrow(() -> new NotFoundException("Laboratory not found!"));
	}
}
