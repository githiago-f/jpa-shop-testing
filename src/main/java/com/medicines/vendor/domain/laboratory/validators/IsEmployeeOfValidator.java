package com.medicines.vendor.domain.laboratory.validators;

import com.medicines.vendor.application.security.IAuthenticationFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.security.Principal;
import java.util.List;

public class IsEmployeeOfValidator implements ConstraintValidator<IsEmployeeOf, Long> {
	private final EntityManager entityManager;
	private final Authentication authentication;

	@Autowired
	public IsEmployeeOfValidator(EntityManager entityManager, IAuthenticationFacade authenticationFacade) {
		this.entityManager = entityManager;
		this.authentication = authenticationFacade.getAuthentication();
	}

	@Override
	public boolean isValid(Long laboratoryId, ConstraintValidatorContext constraintValidatorContext) {
		if(!authentication.isAuthenticated()) {
			throw new RuntimeException("Should be authenticated");
		}
		String username = ((Principal) authentication.getPrincipal()).getName();
		String sql = "SELECT l.employees employees FROM Laboratory l " +
			"JOIN FETCH l.employees e ON e.email = ?2" +
			"WHERE l.id = ?1";
	 	List<?> resultList = entityManager.createQuery(sql)
			.setParameter(1, laboratoryId)
			.setParameter(2, username)
			.getResultList();
		return resultList.size() >= 1;
	}
}
