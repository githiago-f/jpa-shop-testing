package com.medicines.vendor.domain.laboratory.validators;

import com.medicines.vendor.application.security.IAuthenticationFacade;
import com.medicines.vendor.application.security.errors.Unauthorized;
import com.medicines.vendor.domain.laboratory.service.errors.NotEmployee;
import com.medicines.vendor.domain.users.Employee;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.UUID;

public class IsEmployeeOfValidator implements ConstraintValidator<IsEmployeeOf, UUID> {
	private final Logger logger = LogManager.getLogger(IsEmployeeOfValidator.class);
	private final Authentication authentication;

	@Autowired
	public IsEmployeeOfValidator(IAuthenticationFacade authenticationFacade) {
		this.authentication = authenticationFacade.getAuthentication();
	}

	@Override
	public boolean isValid(UUID laboratoryId, ConstraintValidatorContext constraintValidatorContext) {
		if(!authentication.isAuthenticated()) {
			throw new Unauthorized();
		}
		if(!(authentication.getPrincipal() instanceof Employee)) {
			throw new NotEmployee();
		}

		Employee employee = ((Employee) authentication.getPrincipal());

		logger.info("Expect = " + laboratoryId);
		logger.info("To be = " + employee.getLaboratory().getId());

		return employee.getLaboratory()
			.getId()
			.equals(laboratoryId);
	}
}
