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
@RequestMapping("/teachers")
public class TeacherController {

    StudentTeacherService service;

    @Autowired
    public TeacherController(StudentTeacherService service){
        this.service = service;
    }

    @PostMapping("")
    public void addTeacher(@Valid @RequestBody Teacher teacher) {
        service.saveTeacher(teacher);
    }

    @PutMapping("/{id}")
    public void updateTeacher(@PathVariable int id, @Valid @RequestBody Teacher updatedTeacher){
        Teacher teacher = service.getTeacher(id);
        if(teacher != null){
            teacher.setFirstName(updatedTeacher.getFirstName());
            teacher.setLastName(updatedTeacher.getLastName());
            teacher.setEmail(updatedTeacher.getEmail());
            teacher.setAge(updatedTeacher.getAge());
            teacher.setSubject(updatedTeacher.getSubject());
        }
        service.saveTeacher(teacher);
    }


    @DeleteMapping("/{id}")
    public void deleteTeacher(@PathVariable("id") int id) {
        service.deleteTeacher(id);
    }

    @GetMapping("")
    public Page<Teacher> getTeachers(@RequestParam(defaultValue = "idTeacher")String sortBy,
                                     @RequestParam(defaultValue = "0") int pageNumber,
                                     @RequestParam(defaultValue = "100") int pageSize,
                                     @RequestParam(defaultValue = "true") boolean asc) {
        return service.getAllTeachers(pageNumber, pageSize, sortBy, asc);
    }

    @GetMapping("/{field}")
    public List<Teacher> getTeachersWithName(@PathVariable  String field){
        return service.getTeachersWithName(field);
    }

    @GetMapping("/{id}/students")
    public List<Student> getAllTeacherStudents(@PathVariable int id){
        return service.getAllTeacherStudents(id);
    }



    @PostMapping("/{id}/students")
    public void assignStudent(@PathVariable int id, @RequestParam int student_id){
        service.assignTeacherToStudent(student_id, id);
    }

    @DeleteMapping("/{id}/students")
    public void removeTeacher(@PathVariable int id, @RequestParam int student_id){
        service.removeTeacherFromUser(student_id, id);
    }


}
