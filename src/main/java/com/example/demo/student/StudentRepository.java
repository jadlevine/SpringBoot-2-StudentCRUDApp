package com.example.demo.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// annotation below is b/c this is for data access layer
@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

}
