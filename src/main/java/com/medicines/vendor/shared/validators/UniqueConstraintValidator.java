package com.medicines.vendor.shared.validators;

import com.medicines.vendor.shared.errors.ResourceAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class UniqueConstraintValidator implements ConstraintValidator<Unique, Object> {
	private final EntityManager entityManager;
	private Class<?> entity;
	private String field;
	private String entityName;

	@Autowired
	public UniqueConstraintValidator(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@Override
	public void initialize(Unique constraintAnnotation) {
		entity = constraintAnnotation.entity();
		field = constraintAnnotation.field();
		String[] fullyQualifiedNameSplit = entity.getName().split("\\.");
		entityName = fullyQualifiedNameSplit[fullyQualifiedNameSplit.length-1];
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext constraintValidatorContext) {
		String jpql = "SELECT e FROM " + entity.getName() + " e WHERE e." + field + " = :value";
		List<?> resultList = entityManager.createQuery(jpql)
			.setParameter("value", value)
			.getResultList();
		if(resultList.size() >= 1) {
			String message = String.format("%s with %s equal to %s already exists.", entityName, field, value);
			throw new ResourceAlreadyExistsException(message);
		}
		return true;
	}
}
