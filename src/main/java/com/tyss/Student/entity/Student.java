package com.tyss.Student.entity;

import com.tyss.Student.Utility.Gender;
import com.tyss.Student.validators.ValidateSubjects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "data")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {
  @Id
  private String id;
  @NotNull(message = "first name cannot be null")
  private String firstName;
  @Size(min = 5,max = 20, message = "not valid")
  private String lastName;
  @Indexed(unique = true)
  @Email
  private String email;
  private Gender gender;
  @Valid
  private Address address;
  @ValidateSubjects
  private List<String> favSubjects;
  private BigDecimal totalSpentInBooks;
  private LocalDateTime created;


}
