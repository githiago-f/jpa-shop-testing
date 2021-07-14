package com.medicines.vendor.domain.users.services;

import com.medicines.vendor.domain.resource_models.ConsumerModelAssembler;
import com.medicines.vendor.domain.users.Consumer;
import com.medicines.vendor.domain.users.repository.ConsumerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ConsumerService {
	private final ConsumerRepository repository;
	private final ConsumerModelAssembler assembler;

	@Autowired
	public ConsumerService(ConsumerRepository repository, ConsumerModelAssembler assembler) {
		this.repository = repository;
		this.assembler = assembler;
	}

	public EntityModel<Consumer> saveConsumer(Consumer consumer) {
		return assembler.toModel(repository.save(consumer));
	}

	public Optional<EntityModel<Consumer>> getConsumer(Long id) {
		return Optional.of(repository.getById(id))
			.map(assembler::toModel);
	}
}
