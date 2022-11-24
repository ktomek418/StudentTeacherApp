package com.us.studentTeacherApp.repository;

import com.us.studentTeacherApp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findStudentByFirstName(String firstName);
    List<Student> findStudentByLastName(String lastName);

}
