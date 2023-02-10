package com.example.demo.student;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// this has to be instantiated (aka, it is a "Spring bean(?)")
// @Component // spring has specialized Components, here, we want a "service"
@Service // same as "Component", but used here for Semantics, to make it more readable...
         // we know that this class is to be used as a service class

public class StudentService {
  private final StudentRepository studentRepository;

  @Autowired
  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public List<Student> getStudents() {
    return studentRepository.findAll(); // will return a list (this is magic of spring boot)
  }

  public void addNewStudent(Student student) {
    Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
    if (studentOptional.isPresent()) {
      throw new IllegalStateException("email taken");
    }
    // else if student is NOT present in DB, save student to db (or maybe do some
    // email validation middleware stuff)
    studentRepository.save(student);
  }

  public void deleteStudent(Long studentId) {
    boolean exists = studentRepository.existsById(studentId);
    if (!exists) {
      throw new IllegalStateException("student with id " + studentId + " does not exist");
    }
    studentRepository.deleteById(studentId);
  }

  @Transactional // this annotation pulls entity into a managed state (Student student below)
                 // (covered in Spring data JPA)... so, you don't have to implement any jpql
                 // query (like the one in studentRepository), and can use setters from entity to
                 // update entity in database
  public void updateStudent(Long studentId, String name, String email) {
    // boolean exists = studentRepository.existsById(studentId);
    // if (!exists) {
    // throw new IllegalStateException("student with id " + studentId + " does not
    // exist");
    // }
    Student student = studentRepository.findById(studentId)
        .orElseThrow(() -> new IllegalStateException("student with id " + studentId + " does not exist"));

    // last condition below - if name provided is not equal to the current name
    if (name != null && name.length() > 0 && !Objects.equals(student.getName(), name)) {
      student.setName(name);
    }
    if (email != null && email.length() > 0 && !Objects.equals(student.getEmail(), email)) {
      // check that email has not yet been taken
      Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
      if (studentOptional.isPresent()) {
        throw new IllegalStateException("email taken");
      }
      student.setEmail(email);
    }
  }

}