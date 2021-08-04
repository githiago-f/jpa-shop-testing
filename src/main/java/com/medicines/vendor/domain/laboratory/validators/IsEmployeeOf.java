package com.medicines.vendor.domain.laboratory.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = IsEmployeeOfValidator.class)
@Target({ ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface IsEmployeeOf {
	String message() default "Is not employee of this laboratory";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default {};
}
