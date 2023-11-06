package com.betrybe.agrix.controller.advice;

import com.betrybe.agrix.service.exception.CropsNotFoundException;
import com.betrybe.agrix.service.exception.FarmNotFoundException;
import com.betrybe.agrix.service.exception.FertilizerNotFoundException;
import com.betrybe.agrix.service.exception.PersonNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * .
 */
@ControllerAdvice
public class GlobalControllerAdvice {

  @ExceptionHandler({
      FarmNotFoundException.class,
      CropsNotFoundException.class,
      FertilizerNotFoundException.class,
      PersonNotFoundException.class
  })
  public ResponseEntity<String> handleNotFound(RuntimeException exception) {
    return ResponseEntity.status(HttpStatus.NOT_FOUND)
        .body(exception.getMessage());
  }
}
