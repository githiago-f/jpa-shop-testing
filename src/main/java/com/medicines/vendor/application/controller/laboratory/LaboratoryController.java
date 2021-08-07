package com.medicines.vendor.application.controller.laboratory;

import com.medicines.vendor.application.hateoas.laboratory.LaboratoryAssembler;
import com.medicines.vendor.domain.laboratory.Laboratory;
import com.medicines.vendor.domain.laboratory.service.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/laboratory")
public class LaboratoryController {
	final LaboratoryService laboratoryService;
	final LaboratoryAssembler laboratoryAssembler;

	@Autowired
	public LaboratoryController(LaboratoryService laboratoryService,
															LaboratoryAssembler laboratoryAssembler) {
		this.laboratoryService = laboratoryService;
		this.laboratoryAssembler = laboratoryAssembler;
	}

	@GetMapping
	public CollectionModel<EntityModel<Laboratory>> listAllLaboratories(Pageable pageable) {
		Page<Laboratory> laboratories = laboratoryService.listLaboratories(pageable);
		return laboratoryAssembler.toCollectionModel(laboratories);
	}

	@GetMapping("/{id}")
	public EntityModel<Laboratory> laboratoryData(@PathVariable("id")UUID uuid) {
		Laboratory laboratory = laboratoryService.findLaboratory(uuid);
		return laboratoryAssembler.toModel(laboratory);
	}
}
