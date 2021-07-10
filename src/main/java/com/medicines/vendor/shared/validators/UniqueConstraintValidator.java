package com.medicines.vendor.shared.validators;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueConstraintValidator implements ConstraintValidator<Unique, Object> {
	private final EntityManager entityManager;
	private Class<?> entity;

	@Autowired
	public UniqueConstraintValidator(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void initialize(Unique constraintAnnotation) {
		entity = constraintAnnotation.entity();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
		String jpql = "SELECT e FROM " + entity.getName() + " e WHERE ";
		entityManager.createQuery(jpql).setParameter("", value);
		return false;
	}
}
