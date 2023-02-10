package com.example.demo.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

// annotation below is b/c this is for data access layer
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

  // SQL --> SELECT * FROM student WHERE email = ?
  // two ways to do it
  // below, Student is the Student Entity from Student.java
  @Query("SELECT s FROM Student s Where s.email = ?1")
  Optional<Student> findStudentByEmail(String email);
}
