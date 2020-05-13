package ch.lucbu.m151.webshop.model.dto;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

public class ValidationException extends Exception {
  private static final long serialVersionUID = -3542585655452578703L;

  private final Set<String> validations;

  public ValidationException(Set<ConstraintViolation<Object>> validations) {
    super(validations.stream().map(validation -> validation.getMessage()).collect(Collectors.joining(",")));
    this.validations = validations.stream().map(validation -> validation.getMessage()).collect(Collectors.toSet());
  }

  public Set<String> getValidations() {
    return validations;
  }
}
