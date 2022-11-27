package com.us.studentTeacherApp.repository;

import com.us.studentTeacherApp.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.validation.constraints.Size;
import java.util.List;


public interface StudentRepository extends JpaRepository<Student, Integer> {

    List<Student> findStudentByFirstNameOrLastName(String firstName, String lastName);


}
