package com.tyss.Student.Controller;

import com.tyss.Student.Dto.ResponseStructure;
import com.tyss.Student.Dto.StudentAddressDto;
import com.tyss.Student.Dto.StudentResponse;
import com.tyss.Student.entity.BeanScope;
import com.tyss.Student.entity.Student;
import com.tyss.Student.repositoty.StudentRepo;
import com.tyss.Student.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@Slf4j
@RequestMapping("/students")
public class StudentController {

  @Autowired
  private StudentRepo studentRepo;
  @Autowired
  private StudentService studentService;


//  public void redirect(HttpServletResponse response) throws IOException {
//    response.sendRedirect("/swagger-ui.html");
//  }


  @PostMapping("/save")
  public ResponseEntity<?> saveStudent(@RequestBody @Valid Student student) {

//    if(bindingResult.hasErrors())
//    {
//      return ResponseEntity.badRequest().body(
//        bindingResult.getAllErrors().stream().map(ObjectError::getDefaultMessage).collect(Collectors.joining()));
//    }

    return studentService.createStudent(student);
//    return ResponseEntity.accepted().body(student);

  }

  @GetMapping("/findAll")
  public ResponseEntity<ResponseStructure<StudentResponse>> findAll() {
    MDC.put("userid", "siddu");

    log.info("Info Loging in Student Find All");
    MDC.clear();


    return studentService.findAll();
  }

  @Autowired
  MongoTemplate mongoTemplate;


  @GetMapping("/get/prefix/{prefix}")
  public ResponseEntity<ResponseStructure<List<Student>>> startsWithName(@PathVariable String prefix) {
    return studentService.startsWithName(prefix);
  }

  @GetMapping("/get/prefix")
  public ResponseEntity<ResponseStructure<List<Student>>> endsWithName(@RequestParam(required = true, defaultValue = "Doe") String suffix) {

    return studentService.endsWithName(suffix);


  }

  @GetMapping("/get/letter/{letter}")
  public ResponseEntity<ResponseStructure<List<Student>>> findAnyPart(@PathVariable String letter) {
    return studentService.findAnyPart(letter);


  }

  @GetMapping("/get/letter/{prefix}/{suffix}")
  public ResponseEntity<ResponseStructure<List<Student>>> findByBothPart(@PathVariable String prefix, @PathVariable String suffix) {
    return studentService.findBothFistAndLast(prefix, suffix);


  }

  @PutMapping("/update")
  public ResponseEntity<ResponseStructure<Student>> updateStudent(@RequestParam String studentId, @RequestParam String lastName, @RequestParam String email) {

    return studentService.updateStudent(studentId, lastName, email);
  }

  @GetMapping("/filter")
  public ResponseEntity<ResponseStructure<StudentResponse>> filterStudents(@RequestBody List<String> favSubjects) {

    return studentService.filterStudents(favSubjects);
  }

  @PostMapping("/saveAll")
  public ResponseEntity<ResponseStructure<Student>> saveStudent(@RequestBody @Valid List<Student> student) {

    for (Student student1 : student) {
      List<String> fav = student1.getFavSubjects().stream().map(String::toLowerCase).collect(Collectors.toList());
      student1.setFavSubjects(fav);
      student1.setCreated(LocalDateTime.now());
      studentRepo.save(student1);
    }
    return null;
  }

  @GetMapping("/get/{id}")
  public ResponseEntity<?> fetchFavSub(@PathVariable String id) {

    return studentService.fetchFavSubs(id);
  }

  @GetMapping("/gett/{name}")
  public List<Student> fetch(@PathVariable String name) {

    return studentRepo.findSort(name, Sort.by(Sort.Order.asc("lastName")));
  }

  @GetMapping("/fetchh")
  public ResponseEntity<ResponseStructure<StudentResponse<StudentAddressDto>>> fetch() {
    return studentService.findAllUsinfMOdeMapper();
  }


  @Autowired
  private BeanScope beanScope1;
  @Autowired
  private BeanScope beanScope2;

  @GetMapping("/bean")
  public void getBean() {
    System.out.println(beanScope1);
    System.out.println(beanScope2);
  }

  @GetMapping("/beann")
  public void getBeann() {
    System.out.println(beanScope1);
    System.out.println(beanScope2);
  }


}
