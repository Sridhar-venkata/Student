package com.tyss.Student.Dao;

import com.tyss.Student.entity.Student;
import com.tyss.Student.repositoty.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class StudentDao {

  @Autowired
  StudentRepo studentRepo;
  @Autowired
  MongoTemplate mongoTemplate;

  public Student createStudent(Student student) {

    List<String> fav = student.getFavSubjects().stream().map(String::toLowerCase).collect(Collectors.toList());
    student.setFavSubjects(fav);
    student.setCreated(LocalDateTime.now());
    return studentRepo.save(student);
  }

  public List<Student> findAll() {

    return studentRepo.findAll();
  }

  public List<Student> startsWithName(String prefix) {
    Query query = new Query();
    query.addCriteria(Criteria.where("firstName").regex("^" + prefix, "i"));
    return mongoTemplate.find(query, Student.class);
  }

  public List<Student> endswithName(String suffix) {
    Query query = new Query();
    query.addCriteria(Criteria.where("lastName").regex(suffix + "$", "i"));
    return mongoTemplate.find(query, Student.class);
  }

  public List<Student> findAnyPart(String letter) {
    Query query = new Query();
    Criteria criteria = new Criteria();
    query.addCriteria(criteria.orOperator(
      Criteria.where("firstName").regex(letter, "i"),
      Criteria.where("lastName").regex(letter, "i")
    ));
    return mongoTemplate.find(query, Student.class);
  }

  public Student findById(String studentId) {
    Optional<Student> student = studentRepo.findById(studentId);
    return student.orElse(null);

  }

  public List<Student> filter(String sub1, String sub2, String sub3) {
    List<Student> students = studentRepo.findAll();
    List<Student> favStudents = students.stream()
      .filter(student -> student.getFavSubjects().stream()
        .anyMatch(subject -> subject.equalsIgnoreCase(sub1) || subject.equalsIgnoreCase(sub2) || subject.equalsIgnoreCase(sub3)))
      .sorted(Comparator.comparingInt(student ->
          (int) ((Student) student).getFavSubjects().stream()
            .filter(subject -> subject.equalsIgnoreCase(sub1) || subject.equalsIgnoreCase(sub2) || subject.equalsIgnoreCase(sub3))
            .count())
        .reversed())
      .collect(Collectors.toList());
    return favStudents;
  }

  public List<Student> filterBy(List<String> favSubjects) {
    List<Student> students = studentRepo.findStudentsByFavSubjects(favSubjects.stream().map(String::toLowerCase).collect(Collectors.toList()));
    students = students.stream()
      .peek(student -> {
        Collections.sort(student.getFavSubjects());
      })
      .collect(Collectors.toList());

    return students;
  }
}
