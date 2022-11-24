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

    @GetMapping("")
    public List<Student> getStudents() {
        return service.getAllStudents();
    }

    @GetMapping("/firstNameLike/{firstName}")
    public List<Student> getStudentsByFirstName(@PathVariable String firstName){
        return service.getAllStudentWithFirstName(firstName);}

    @GetMapping("/lastNameLike/{lastName}")
    public List<Student> getStudentsByLastName(@PathVariable String lastName){
        return service.getAllStudentWithLastName(lastName);}

    @GetMapping("sortBy/{field}")
    public List<Student> getStudentsSorted(@PathVariable String field) {
        return service.getAllStudentsSorted(field);
    }

    @GetMapping("/{pageNumber}/{pageSize}")
    public Page<Student> getStudentsWithPagination(@PathVariable int pageNumber, @PathVariable int pageSize){
        return service.getAllStudentWithPagination(pageNumber, pageSize);
    }

    @GetMapping("/sortBy/{field}/{pageNumber}/{pageSize}")
    public Page<Student> getStudentsSortedWithPagination(
            @PathVariable String field, @PathVariable int pageNumber, @PathVariable int pageSize){
        return service.getAllStudentSortedWithPagination(pageNumber, pageSize, field);
    }

    @PostMapping("/add")
    public void addStudent(@Valid @RequestBody Student student) {
        service.saveStudent(student);
    }

    @DeleteMapping("/{id}/delete")
    public void deleteStudent(@PathVariable("id") int id) {
        service.deleteStudent(id);
    }

    @PutMapping("/{id}/update")
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

    @PostMapping("/{id}/assignTeacher")
    public void assignTeacher(@PathVariable int id, @RequestParam int teacher_id){
        service.assignTeacherToStudent(id, teacher_id);
    }

    @PostMapping("/{id}/removeTeacher")
    public void removeTeacher(@PathVariable int id, @RequestParam int teacher_id){
        service.removeTeacherFromUser(id, teacher_id);
    }

    @GetMapping("/{id}/allTeachers")
    public List<Teacher> getAllStudentTeachers(@PathVariable int id){
        return service.getAllStudentTeachers(id);
    }

}
