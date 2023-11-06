package com.betrybe.agrix.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * .
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class FarmNotFoundException extends RuntimeException {
  public FarmNotFoundException() {
    super("Fazenda não encontrada!");
  }
}
