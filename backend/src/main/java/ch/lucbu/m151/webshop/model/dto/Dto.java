package ch.lucbu.m151.webshop.model.dto;

import java.lang.reflect.Field;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import ch.lucbu.m151.webshop.exception.ValidationException;

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

  public void expand(Dto dto) {
    Class<? extends Dto> dtoClass = getClass();
    if (dtoClass.equals(dto.getClass())) {
      for (Field field : dtoClass.getFields()) {
        try {
          if (field.get(dto) != null) {
            field.set(this, field.get(dto));
          }
        } catch (IllegalArgumentException | IllegalAccessException e) {
          return;
        }
      }
    }
  }
}
