package com.tyss.Student.Exception;


public class NoStudentDetailsFoundException extends RuntimeException {

  private String msg;

  public NoStudentDetailsFoundException(String message) {

    this.msg = message;
  }

  @Override
  public String getMessage() {
    return msg;
  }
}
