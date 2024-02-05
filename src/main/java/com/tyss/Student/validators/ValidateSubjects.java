package com.tyss.Student.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Target({ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomValidator.class)
public @interface ValidateSubjects {

  public String message()  default "Some Subjects are not Available to Register";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
