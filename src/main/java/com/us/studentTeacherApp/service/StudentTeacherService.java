package com.us.studentTeacherApp.service;


import com.us.studentTeacherApp.model.Student;
import com.us.studentTeacherApp.model.Teacher;
import com.us.studentTeacherApp.repository.StudentRepository;
import com.us.studentTeacherApp.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;


@Service
public class StudentTeacherService {

    StudentRepository studentRepo;
    TeacherRepository teacherRepo;

    @Autowired
    public StudentTeacherService(StudentRepository studentRepo, TeacherRepository teacherRepo) {
        this.studentRepo = studentRepo;
        this.teacherRepo = teacherRepo;
    }

    @PostConstruct
    public void initMyDb(){
        studentRepo.save(new Student("Marcin", "Rak", 22, "mar@gmail.com", "cs"));
        studentRepo.save(new Student("Krzysztof", "Kowalski", 19, "krzy@gmail.com", "cs"));
        studentRepo.save(new Student("Daniel", "Lewy", 34, "dan@gmail.com", "cs"));
        studentRepo.save(new Student("Jan", "Drugi", 23, "jang@gmail.com", "cs"));
        studentRepo.save(new Student("Szczepan", "Kowalski", 33, "szcz@gmail.com", "cs"));
        studentRepo.save(new Student("Jan", "Stefan", 22, "ja@gmail.com", "cs"));
        studentRepo.save(new Student("Marcin", "Kowalski", 22, "mar@gmail.com", "cs"));

        teacherRepo.save(new Teacher("Ola", "Rak", 44, "ol@gmail.com", "cs"));
        teacherRepo.save(new Teacher("Tomek", "Rak", 55, "tom@gmail.com", "cs"));
        teacherRepo.save(new Teacher("Sandra", "Lewy", 33, "san@gmail.com", "cs"));
        teacherRepo.save(new Teacher("Jagoda", "Kowalski", 55, "jag@gmail.com", "cs"));
        teacherRepo.save(new Teacher("Tomek", "Drugi", 55, "tom2@gmail.com", "cs"));
        teacherRepo.save(new Teacher("Ola", "Drugi", 44, "ol2@gmail.com", "cs"));



    }

    public Page<Student> getAllStudents(int pageNumber, int pageSize, String field, boolean asc){
        if(asc){
            return studentRepo.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.by(field)));
        }
        return studentRepo.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.by(field).descending()));
    }

    public List<Student> getStudentsWithName(String field){
        return studentRepo.findStudentByFirstNameOrLastName(field, field);
    }

    public Student getStudent(int id){
        return studentRepo.getReferenceById(id);
    }

    public void deleteStudent(int id){
        studentRepo.deleteById(id);
    }

    public void saveStudent(Student student){
        studentRepo.save(student);
    }

    public List<Teacher> getAllStudentTeachers(int student_id){
        Student student = getStudent(student_id);
        return student.getTeachers();
    }




    public Page<Teacher> getAllTeachers(int pageNumber, int pageSize, String field, boolean asc){
        if(asc){
            return teacherRepo.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.by(field)));
        }
        return teacherRepo.findAll(PageRequest.of(pageNumber, pageSize).withSort(Sort.by(field).descending()));
    }

    public List<Teacher> getTeachersWithName(String name){
        return teacherRepo.findTeacherByFirstNameOrLastName(name, name);
    }

    public Teacher getTeacher(int id){
        return teacherRepo.getReferenceById(id);
    }

    public void deleteTeacher(int id){
        teacherRepo.deleteById(id);
    }

    public void saveTeacher(Teacher teacher){
        teacherRepo.save(teacher);
    }

    public List<Student> getAllTeacherStudents(int teacher_id){
        Teacher teacher = getTeacher(teacher_id);
        return teacher.getStudents();
    }





    public void removeTeacherFromUser(int student_id, int teacher_id){
        Student student = getStudent(student_id);
        Teacher teacher = getTeacher(teacher_id);
        if(student != null && teacher != null){
            System.out.println("1");
            student.removeTeacher(teacher);
            saveStudent(student);
        }
    }


    public void assignTeacherToStudent(int student_id, int teacher_id){
        Student student = getStudent(student_id);
        Teacher teacher = getTeacher(teacher_id);
        if(student != null && teacher != null){
            student.addTeacher(teacher);
            saveStudent(student);
        }
    }

}
