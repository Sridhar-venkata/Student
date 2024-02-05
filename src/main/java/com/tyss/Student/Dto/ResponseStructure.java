package com.tyss.Student.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseStructure<T> {
  private int statusCode;
  private String statusMessage;
  private T data;
}
