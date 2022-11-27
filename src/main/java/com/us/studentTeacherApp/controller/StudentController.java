package com.us.studentTeacherApp.controller;


import com.us.studentTeacherApp.model.Student;
import com.us.studentTeacherApp.model.Teacher;
import com.us.studentTeacherApp.service.StudentTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    StudentTeacherService service;

    @Autowired
    public StudentController(StudentTeacherService service){
        this.service = service;
    }

    @PostMapping("")
    public void addStudent(@Valid @RequestBody Student student) {
        service.saveStudent(student);
    }

    @PutMapping("/{id}")
    public void updateStudent(@PathVariable int id,@Valid @RequestBody Student updatedStudent){
        Student student = service.getStudent(id);
        if(student != null){
            student.setFirstName(updatedStudent.getFirstName());
            student.setLastName(updatedStudent.getLastName());
            student.setEmail(updatedStudent.getEmail());
            student.setAge(updatedStudent.getAge());
            student.setMajor(updatedStudent.getMajor());
        }
        service.saveStudent(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable int id) {
        service.deleteStudent(id);
    }


    @GetMapping("")
    public Page<Student> getStudents(@RequestParam(defaultValue = "idStudent")String sortBy,
                                     @RequestParam(defaultValue = "0") int pageNumber,
                                     @RequestParam(defaultValue = "100") int pageSize,
                                     @RequestParam(defaultValue = "true") boolean asc){
        return service.getAllStudents(pageNumber, pageSize, sortBy, asc);
    }

    @GetMapping("/{field}")
    public List<Student> getStudents(@PathVariable String field){
        return service.getStudentsWithName(field);
    }

    @GetMapping("/{id}/teachers")
    public List<Teacher> getAllStudentTeachers(@PathVariable int id){
        return service.getAllStudentTeachers(id);
    }

    @PostMapping("/{id}/teachers")
    public void assignTeacher(@PathVariable int id, @RequestParam int teacher_id){
        service.assignTeacherToStudent(id, teacher_id);
    }

    @DeleteMapping("/{id}/teachers")
    public void removeTeacher(@PathVariable int id, @RequestParam int teacher_id){
        service.removeTeacherFromUser(id, teacher_id);
    }

}
