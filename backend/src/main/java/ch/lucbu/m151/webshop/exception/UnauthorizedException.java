package ch.lucbu.m151.webshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.UNAUTHORIZED)
public class UnauthorizedException extends RuntimeException {
  private static final long serialVersionUID = 1490020994108602501L;

  public UnauthorizedException() {
    super("Sie sind für diesen Vorgang nicht berechtigt!");
  }
}
