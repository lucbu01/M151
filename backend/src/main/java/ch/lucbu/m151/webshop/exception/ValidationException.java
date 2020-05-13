package ch.lucbu.m151.webshop.exception;

import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class ValidationException extends RuntimeException {
  private static final long serialVersionUID = -3542585655452578703L;

  private final Set<String> validations;

  public ValidationException(Set<ConstraintViolation<Object>> validations) {
    super(validations.stream().map(validation -> validation.getPropertyPath() + " " + validation.getMessage())
        .collect(Collectors.joining(",")));
    this.validations = validations.stream()
        .map(validation -> validation.getPropertyPath() + " " + validation.getMessage()).collect(Collectors.toSet());
  }

  public Set<String> getValidations() {
    return validations;
  }
}
