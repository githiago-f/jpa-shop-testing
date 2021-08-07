package com.medicines.vendor.application.hateoas.laboratory;

import com.medicines.vendor.application.controller.laboratory.LaboratoryController;
import com.medicines.vendor.domain.laboratory.Laboratory;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.UUID;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class LaboratoryAssembler implements SimpleRepresentationModelAssembler<Laboratory> {
	final LaboratoryController ref = methodOn(LaboratoryController.class);

	@Override
	public EntityModel<Laboratory> toModel(Laboratory entity) {
		return SimpleRepresentationModelAssembler.super.toModel(entity);
	}

	@Override
	public void addLinks(EntityModel<Laboratory> resource) {
		Laboratory laboratory = resource.getContent();
		assert laboratory != null;
		UUID uuid = laboratory.getId();
		resource.add(
			linkTo(ref.laboratoryData(uuid)).withSelfRel()
		);
	}

	@Override
	public CollectionModel<EntityModel<Laboratory>> toCollectionModel(Iterable<? extends Laboratory> entities) {
		return SimpleRepresentationModelAssembler.super.toCollectionModel(entities);
	}

	@Override
	public void addLinks(CollectionModel<EntityModel<Laboratory>> resources) {
		Link link = linkTo(
			ref.listAllLaboratories(Pageable.ofSize(1))
		).withRel("page");
		resources.add(link);
	}
}
