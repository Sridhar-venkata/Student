package com.tyss.Student.Exception;

import com.tyss.Student.Dto.ResponseStructure;
import org.springframework.dao.DataAccessResourceFailureException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class StudentExceptionHandler {

  @ExceptionHandler(NoStudentDetailsFoundException.class)
  public ResponseEntity<ResponseStructure<String>> noStudentDetailsFound(NoStudentDetailsFoundException exception) {
    ResponseStructure<String> responseStructure = new ResponseStructure<>(HttpStatus.NOT_FOUND.value(), exception.getMessage(), null);
    return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(EmailAlreayFoundException.class)
  public ResponseEntity<ResponseStructure<String>> emailAlreayFound(EmailAlreayFoundException exception) {
    ResponseStructure<String> responseStructure = new ResponseStructure<>(HttpStatus.FOUND.value(), exception.getMessage(), null);
    return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.FOUND);
  }

  @ExceptionHandler(DataAccessResourceFailureException.class)
  public ResponseEntity<ResponseStructure<String>> dataAccessResourceFailureException(DataAccessResourceFailureException exception) {
    ResponseStructure<String> responseStructure = new ResponseStructure<>(HttpStatus.SERVICE_UNAVAILABLE.value(), "Server Not Found", exception.getMessage());
    return new ResponseEntity<ResponseStructure<String>>(responseStructure, HttpStatus.SERVICE_UNAVAILABLE);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
//  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ResponseEntity<ResponseStructure<Map<String, String>>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
    ResponseStructure<Map<String, String>> responseStructure = new ResponseStructure<>(HttpStatus.BAD_REQUEST.value(), "BAD REQUEST", errors);
    return new ResponseEntity<ResponseStructure<Map<String, String>>>(responseStructure, HttpStatus.BAD_REQUEST);

  }

}
