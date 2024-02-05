package com.tyss.Student.Dto;

import com.tyss.Student.entity.Student;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class StudentResponse<T> {

  private List<T> students;


}
