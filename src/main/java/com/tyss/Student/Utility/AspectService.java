package com.tyss.Student.Utility;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Aspect
@Slf4j
@Component
public class AspectService {

  @Pointcut("execution(public * com.tyss.Student.Controller.*.findAll(..))")
  public void p1() {

  }

  @Before("p1()")
  public void beforeMethod() {
    log.info("Method Started succesfully");
    //System.out.println("Method Started......");
  }

  @After("p1()")
  public void afterMethod() {
    //System.out.println("Method Execution Completed...");
    log.info("Method Execution Completed...");
  }

  @AfterReturning("p1()")
  public void afterReturnMethod() {
    //System.out.println("Method Execution Completed...");
    log.info("Method Execution Completed REturn...");
  }

//  @Around("p1()")
//  public void aroundMethod(ProceedingJoinPoint jp) throws Throwable {
//        jp.proceed();
//  }
}
