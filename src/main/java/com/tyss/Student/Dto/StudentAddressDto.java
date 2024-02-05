package com.tyss.Student.Dto;

import com.tyss.Student.Utility.Gender;
import com.tyss.Student.entity.Address;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class StudentAddressDto {


  private String firstName;
  private String lastName;
  @Indexed(unique = true)
  private String email;
  private Gender gender;
  private String city;
  private String country;
  private List<String> favSubjects;
  private BigDecimal totalSpentInBooks;

}
