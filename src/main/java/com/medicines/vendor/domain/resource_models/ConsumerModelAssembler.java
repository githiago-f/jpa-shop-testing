package com.medicines.vendor.domain.resource_models;

import com.medicines.vendor.application.controller.ConsumerController;
import com.medicines.vendor.domain.users.Consumer;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;


@Component
public class ConsumerModelAssembler implements SimpleRepresentationModelAssembler<Consumer> {
	private final ConsumerController rel = methodOn(ConsumerController.class);

	@Override
	public EntityModel<Consumer> toModel(Consumer entity) {
		return EntityModel.of(entity);
	}

	@Override
	public void addLinks(EntityModel<Consumer> resource) {
		Consumer medicine = resource.getContent();
		assert medicine != null;
		Link link = linkTo(rel.findOne(medicine.getId())).withSelfRel();
		resource.add(link);
	}

	@Override
	public CollectionModel<EntityModel<Consumer>> toCollectionModel(Iterable<? extends Consumer> entities) {
		return SimpleRepresentationModelAssembler.super.toCollectionModel(entities);
	}

	@Override
	public void addLinks(CollectionModel<EntityModel<Consumer>> resources) { }
}
