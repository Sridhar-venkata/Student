package com.tyss.Student;


import com.tyss.Student.entity.BeanScope;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;


@SpringBootApplication
@EnableAspectJAutoProxy
public class StudentApplication {


  public static void main(String[] args) {
    SpringApplication.run(StudentApplication.class, args);



  }

}
