package fr.mathunaki.database.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = PhoneNumberValidator.class)
public @interface PhoneNumber {

	String message() default "{fr.mathunaki.validation.constraints.PhoneNumber}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}