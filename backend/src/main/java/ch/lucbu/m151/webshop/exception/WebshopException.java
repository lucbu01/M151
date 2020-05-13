package ch.lucbu.m151.webshop.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
public class WebshopException extends RuntimeException {

  public WebshopException() {
    super();
  }

  public WebshopException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }

  public WebshopException(String message, Throwable cause) {
    super(message, cause);
  }

  public WebshopException(String message) {
    super(message);
  }

  public WebshopException(Throwable cause) {
    super(cause);
  }

}
