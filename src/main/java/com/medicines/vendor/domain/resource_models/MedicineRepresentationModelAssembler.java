package com.medicines.vendor.domain.resource_models;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import com.medicines.vendor.application.controller.MedicineController;
import com.medicines.vendor.domain.medicine.Medicine;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MedicineRepresentationModelAssembler implements SimpleRepresentationModelAssembler<Medicine> {
	private final MedicineController ref = methodOn(MedicineController.class);

	@Override
	public EntityModel<Medicine> toModel(Medicine entity) {
		String identifier = entity.getCode();
		Link link = linkTo(ref.getMedicineByCode(identifier)).withSelfRel();
		return EntityModel.of(entity, link);
	}

	@Override
	public void addLinks(EntityModel<Medicine> resource) {
			Medicine medicine = resource.getContent();
			assert medicine != null;
			String identifier = medicine.getCode();
			Link link = linkTo(ref.getMedicineByCode(identifier)).withSelfRel();
			resource.add(link);
	}

	@Override
	public CollectionModel<EntityModel<Medicine>> toCollectionModel(Iterable<? extends Medicine> entities) {
		List<EntityModel<Medicine>> medicines = new ArrayList<>();
		entities.forEach(i -> medicines.add(toModel(i)));
		Link link = linkTo(ref.getMedicines(0)).withRel("page");
		return CollectionModel.of(medicines, link);
	}

	@Override
	public void addLinks(CollectionModel<EntityModel<Medicine>> resources) {
	}
}
