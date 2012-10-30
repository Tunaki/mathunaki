package fr.mathunaki.database.validation;

import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {

	/** Phone number regexp pattern. */
	private static final Pattern PHONE_NUMBER_PATTERN = Pattern.compile("0[1-9][0-9]{8}");

	@Override
	public void initialize(PhoneNumber constraintAnnotation) {
		// nothing to do.
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value == null ? true : PHONE_NUMBER_PATTERN.matcher(value).matches();
	}

}