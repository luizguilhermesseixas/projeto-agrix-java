package com.betrybe.agrix.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * crops notfound exception.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CropsNotFoundException extends RuntimeException {
  public CropsNotFoundException() {
    super("Plantação não encontrada!");
  }
}
