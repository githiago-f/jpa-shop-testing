package com.medicines.vendor.domain.resource_models;

import com.medicines.vendor.domain.users.Consumer;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.SimpleRepresentationModelAssembler;
import org.springframework.stereotype.Component;


@Component
public class ConsumerRepresentationModelAssembler implements SimpleRepresentationModelAssembler<Consumer> {
	@Override
	public EntityModel<Consumer> toModel(Consumer entity) {
		return SimpleRepresentationModelAssembler.super.toModel(entity);
	}

	@Override
	public void addLinks(EntityModel<Consumer> resource) { }

	@Override
	public CollectionModel<EntityModel<Consumer>> toCollectionModel(Iterable<? extends Consumer> entities) {
		return SimpleRepresentationModelAssembler.super.toCollectionModel(entities);
	}

	@Override
	public void addLinks(CollectionModel<EntityModel<Consumer>> resources) { }
}
