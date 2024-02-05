package com.tyss.Student.service;

import com.tyss.Student.Dao.StudentDao;
import com.tyss.Student.Dto.ResponseStructure;
import com.tyss.Student.Dto.StudentAddressDto;
import com.tyss.Student.Dto.StudentResponse;
import com.tyss.Student.Exception.EmailAlreayFoundException;
import com.tyss.Student.Exception.NoStudentDetailsFoundException;
import com.tyss.Student.entity.Student;
import com.tyss.Student.repositoty.StudentRepo;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentService {

  @Autowired
  StudentDao studentDao;
  @Autowired
  StudentRepo studentRepo;

  public ResponseEntity<ResponseStructure<StudentResponse>> findAll() {

    List<Student> studentDetails = studentDao.findAll();
    if (!studentDetails.isEmpty()) {

      ResponseStructure<StudentResponse> studentResponseStructure = new ResponseStructure<>(HttpStatus.FOUND.value(), "Data Found", new StudentResponse(studentDetails));

      return new ResponseEntity<ResponseStructure<StudentResponse>>(studentResponseStructure, HttpStatus.FOUND);
    } else {
      throw new NoStudentDetailsFoundException("No Student Details Found");
    }
  }
  public ResponseEntity<ResponseStructure<StudentResponse<StudentAddressDto>>> findAllUsinfMOdeMapper() {

    List<StudentAddressDto> studentDetails = studentDao.findAll().stream().map(this::convertToDto).collect(Collectors.toList());
    if (!studentDetails.isEmpty()) {

      ResponseStructure<StudentResponse<StudentAddressDto>> studentResponseStructure = new ResponseStructure<>(HttpStatus.FOUND.value(), "Data Found", new StudentResponse(studentDetails));

      return new ResponseEntity<ResponseStructure<StudentResponse<StudentAddressDto>>>(studentResponseStructure, HttpStatus.FOUND);
    } else {
      throw new NoStudentDetailsFoundException("No Student Details Found");
    }
  }

  // Model Mapper
  @Autowired
  private ModelMapper modelMapper;
  public StudentAddressDto convertToDto(Student student)
  {
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.LOOSE);
    StudentAddressDto studentAddressDto;
    //studentAddressDto.setFirstName(student.getFirstName());
    studentAddressDto=modelMapper.map(student,StudentAddressDto.class);
    return studentAddressDto;
  }


  public ResponseEntity<ResponseStructure<?>> createStudent(Student student) {
      Student existingStudent = studentRepo.findByEmail(student.getEmail());
      if (existingStudent == null) {
        Student studentDetails = studentDao.createStudent(student);

        ResponseStructure<Student> studentResponseStructure = new ResponseStructure<>(HttpStatus.CREATED.value(), "Student Data is Added", studentDetails);

        return new ResponseEntity<ResponseStructure<?>>(studentResponseStructure, HttpStatus.CREATED);
      } else {
        throw new EmailAlreayFoundException("Email Id Alreay Found");
      }


  }

  public ResponseEntity<ResponseStructure<List<Student>>> startsWithName(String prefix) {

    List<Student> studentDetails = studentDao.startsWithName(prefix);
    if (!studentDetails.isEmpty()) {
      ResponseStructure<List<Student>> studentResponseStructure = new ResponseStructure<>(HttpStatus.FOUND.value(), "Data Found", studentDetails);

      return new ResponseEntity<ResponseStructure<List<Student>>>(studentResponseStructure, HttpStatus.FOUND);
    } else {
      throw new NoStudentDetailsFoundException("No Student Details Found");
    }
  }

  public ResponseEntity<ResponseStructure<List<Student>>> endsWithName(String suffix) {
    List<Student> studentDetails = studentDao.endswithName(suffix);
    if (!studentDetails.isEmpty()) {
      ResponseStructure<List<Student>> studentResponseStructure = new ResponseStructure<>(HttpStatus.FOUND.value(), "Data Found", studentDetails);

      return new ResponseEntity<ResponseStructure<List<Student>>>(studentResponseStructure, HttpStatus.FOUND);
    } else {
      throw new NoStudentDetailsFoundException("No Student Details Found");
    }
  }

  public ResponseEntity<ResponseStructure<List<Student>>> findAnyPart(String letter) {

    List<Student> studentDetails = studentDao.findAnyPart(letter);
    if (!studentDetails.isEmpty()) {
      ResponseStructure<List<Student>> studentResponseStructure = new ResponseStructure<>(HttpStatus.FOUND.value(), "Data Found", studentDetails);

      return new ResponseEntity<ResponseStructure<List<Student>>>(studentResponseStructure, HttpStatus.FOUND);
    } else {
      throw new NoStudentDetailsFoundException("No Student Details Found");
    }
  }

  public ResponseEntity<ResponseStructure<List<Student>>> findBothFistAndLast(String prefix, String suffix) {
    List<Student> studentDetails = studentRepo.findBothFistAndLast(prefix, suffix);
    ;
    if (!studentDetails.isEmpty()) {
      ResponseStructure<List<Student>> studentResponseStructure = new ResponseStructure<>(HttpStatus.FOUND.value(), "Data Found", studentDetails);

      return new ResponseEntity<ResponseStructure<List<Student>>>(studentResponseStructure, HttpStatus.FOUND);
    } else {
      throw new NoStudentDetailsFoundException("No Student Details Found");
    }
  }

  public ResponseEntity<ResponseStructure<Student>> updateStudent(String studentId, String lastName, String email) {
    Student existingStudent = studentDao.findById(studentId);
    if (existingStudent != null) {
      existingStudent.setLastName(lastName);
      Student existingStudentData = studentRepo.findByEmail(email);
      if (existingStudentData == null) {
        existingStudent.setEmail(email);
        studentDao.createStudent(existingStudent);
        ResponseStructure<Student> studentResponseStructure = new ResponseStructure<>(HttpStatus.OK.value(), "Data updated", existingStudent);

        return new ResponseEntity<ResponseStructure<Student>>(studentResponseStructure, HttpStatus.OK);
      } else {
        throw new EmailAlreayFoundException("Email Id Alreay Found ");
      }
    } else {
      throw new NoStudentDetailsFoundException("Student Id Not Found");
    }


  }

  public ResponseEntity<ResponseStructure<StudentResponse>> filterStudents(List<String> favSubjects) {

    List<Student> students = studentDao.filterBy(favSubjects);

    ResponseStructure<StudentResponse> studentResponseStructure = new ResponseStructure<>(HttpStatus.FOUND.value(), "Data Found", new StudentResponse(students));

    return new ResponseEntity<ResponseStructure<StudentResponse>>(studentResponseStructure, HttpStatus.FOUND);
  }

  public ResponseEntity<ResponseStructure<List<String>>> fetchFavSubs(String id) {

    List<String> favSubjects = studentRepo.fetchFavSubjects(id);

    // favSubjects = Arrays.asList(favSubjects.get(0).split(":")[1].replace("[","").replace("]","").replaceAll("\"","").split(","));

    ResponseStructure<List<String>> studentResponseStructure = new ResponseStructure<>(HttpStatus.OK.value(), "Data Found", favSubjects);

    return new ResponseEntity<ResponseStructure<List<String>>>(studentResponseStructure, HttpStatus.FOUND);
  }
}
