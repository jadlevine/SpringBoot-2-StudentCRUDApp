package com.example.demo.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    return studentRepository.findAll(); // will return a list (this is magic of )
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

}
