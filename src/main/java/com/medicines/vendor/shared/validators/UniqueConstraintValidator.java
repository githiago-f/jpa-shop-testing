package com.medicines.vendor.shared.validators;

import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueConstraintValidator implements ConstraintValidator<Unique, Object> {
	private final EntityManager entityManager;
	private Class<?> entity;
	private String field;

	@Autowired
	public UniqueConstraintValidator(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void initialize(Unique constraintAnnotation) {
		entity = constraintAnnotation.entity();
		field = constraintAnnotation.field();
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
		String jpql = "SELECT e.code FROM " + entity.getName() + " e WHERE e." + field + " = :value";
		List<?> resultList = entityManager.createQuery(jpql)
			.setParameter("value", value.toString())
			.getResultList();
		return resultList.size() < 1;
	}
}
