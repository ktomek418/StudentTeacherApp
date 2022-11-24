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

    @GetMapping("")
    public List<Teacher> getTeachers() {
        return service.getAllTeachers();
    }

    @GetMapping("/firstNameLike/{firstName}")
    public List<Teacher> getTeachersByFirstName(@PathVariable String firstName) {
        return service.getAllTeachersWithFirstName(firstName);
    }

    @GetMapping("/lastNameLike/{lastName}")
    public List<Teacher> getTeachersByLastName(@PathVariable String lastName) {
        return service.getAllTeachersWithLastName(lastName);
    }

    @GetMapping("/sortBy/{field}")
    public List<Teacher> getStudentsSorted(@PathVariable String field) {
        return service.getAllTeachersSorted(field);
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public Page<Teacher> getTeachers(@PathVariable int pageNumber, @PathVariable int pageSize){
        return service.getAllTeachersWithPagination(pageNumber, pageSize);
    }

    @GetMapping("/sortBy/{field}/{pageNumber}/{pageSize}")
    public Page<Teacher> getTeachers( @PathVariable String field, @PathVariable int pageNumber, @PathVariable int pageSize){
        return service.getAllTeachersSortedWithPagination(pageNumber, pageSize, field);
    }

    @PostMapping("/add")
    public void addTeacher(@Valid @RequestBody Teacher teacher) {
        service.saveTeacher(teacher);
    }

    @PutMapping("/{id}/update")
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


    @DeleteMapping("/{id}/delete")
    public void deleteTeacher(@PathVariable("id") int id) {
        service.deleteTeacher(id);
    }

    @PostMapping("/{id}/assignStudent")
    public void assignStudent(@PathVariable int id, @RequestParam int student_id){
        service.assignTeacherToStudent(student_id, id);
    }

    @PostMapping("/{id}/removeStudent")
    public void removeTeacher(@PathVariable int id, @RequestParam int student_id){
        service.removeTeacherFromUser(student_id, id);
    }

    @GetMapping("/{id}/allStudents")
    public List<Student> getAllTeacherStudents(@PathVariable int id){
        return service.getAllTeacherStudents(id);
    }

}
