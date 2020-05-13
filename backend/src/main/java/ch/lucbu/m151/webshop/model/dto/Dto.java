package ch.lucbu.m151.webshop.model.dto;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public abstract class Dto {
  public boolean validate() throws ValidationException {
    ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    Validator validator = factory.getValidator();
    Set<ConstraintViolation<Object>> validations = validator.validate(this);
    if (validations.size() > 0) {
      throw new ValidationException(validations);
    }
    return true;
  }
}
