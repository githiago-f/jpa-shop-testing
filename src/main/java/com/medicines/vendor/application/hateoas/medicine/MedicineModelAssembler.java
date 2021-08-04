package com.medicines.vendor.application.hateoas.medicine;

import com.medicines.vendor.application.controller.medicine.MedicineController;
import com.medicines.vendor.domain.medicine.Medicine;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class MedicineModelAssembler implements SimpleRepresentationModelAssembler<Medicine> {
	private final MedicineController rel = methodOn(MedicineController.class);

	@Override
	public EntityModel<Medicine> toModel(Medicine entity) {
		return SimpleRepresentationModelAssembler.super.toModel(entity);
	}

	@Override
	public void addLinks(EntityModel<Medicine> resource) {
		Medicine medicine = resource.getContent();
		assert medicine != null;
		Link link = linkTo(rel.getMedicineByCode(medicine.getCode())).withSelfRel();
		resource.add(link);
	}

	@Override
	public CollectionModel<EntityModel<Medicine>> toCollectionModel(Iterable<? extends Medicine> entities) {
		return SimpleRepresentationModelAssembler.super.toCollectionModel(entities);
	}

	@Override
	public void addLinks(CollectionModel<EntityModel<Medicine>> resources) {
		Link link = linkTo(
			rel.getMedicines(Pageable.ofSize(1))
		).withRel("page");
		resources.add(link);
	}
}
