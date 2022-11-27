package com.us.studentTeacherApp.repository;


import com.us.studentTeacherApp.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

    List<Teacher> findTeacherByFirstNameOrLastName(String firstName, String lastName);
}
