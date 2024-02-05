package com.tyss.Student.validators;

import com.tyss.Student.Utility.Gender;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CustomValidator implements ConstraintValidator<ValidateSubjects, List<String>> {


  @Override
  public boolean isValid(List<String> listOfSubject, ConstraintValidatorContext constraintValidatorContext) {
//    List<String> subjectsList= Arrays.asList("maths","java","html","css");
//    return subjectsList.containsAll(subject);

    List<String> availableSubjects = Arrays.asList("maths", "java", "html", "css");

    List<String> invalidSubjects = listOfSubject.stream()
      .filter(subject -> !availableSubjects.contains(subject))
      .collect(Collectors.toList());

    if (!invalidSubjects.isEmpty()) {
      String invalidSubjectList = String.join(", ", invalidSubjects);
      String message = "Subjects not available: " + invalidSubjectList;
      constraintValidatorContext.disableDefaultConstraintViolation();
      constraintValidatorContext.buildConstraintViolationWithTemplate(message).addConstraintViolation();
      return false;
    }

    return true;

  }
}
