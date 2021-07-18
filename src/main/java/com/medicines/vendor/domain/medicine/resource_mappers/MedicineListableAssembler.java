package com.medicines.vendor.domain.medicine.resource_mappers;

import com.medicines.vendor.application.controller.medicine.MedicineController;
import com.medicines.vendor.domain.medicine.dto.MedicineListable;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MedicineListableAssembler implements SimpleRepresentationModelAssembler<MedicineListable> {
	private final MedicineController rel = methodOn(MedicineController.class);

	@Override
	public EntityModel<MedicineListable> toModel(MedicineListable entity) {
		return SimpleRepresentationModelAssembler.super.toModel(entity);
	}

	@Override
	public void addLinks(EntityModel<MedicineListable> resource) {
		MedicineListable medicine = resource.getContent();
		assert medicine != null;
		Link link = linkTo(rel.getMedicineByCode(medicine.getCode())).withSelfRel();
		resource.add(link);
	}

	@Override
	public CollectionModel<EntityModel<MedicineListable>> toCollectionModel(Iterable<? extends MedicineListable> entities) {
		return SimpleRepresentationModelAssembler.super.toCollectionModel(entities);
	}

	@Override
	public void addLinks(CollectionModel<EntityModel<MedicineListable>> resources) {
		Link link = linkTo(
			rel.getMedicines(Pageable.ofSize(1))
		).withRel("page");
		resources.add(link);
	}
}
