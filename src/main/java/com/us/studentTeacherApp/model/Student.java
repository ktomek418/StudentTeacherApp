package com.us.studentTeacherApp.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_student;
    @Size(min = 3,message = "Name must be longer than 2 characters")
    private String firstName;
    private String lastName;
    @Min(value = 18)
    private int age;
    @Email(message="Please provide a valid email address")
    private String email;
    private String major;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinTable(
            name = "students_teachers",
            joinColumns = @JoinColumn(name = "id_student"),
            inverseJoinColumns = @JoinColumn(name = "id_teacher")
    )
    private List<Teacher> teachers;

    public Student(String firstName, String lastName, int age, String email, String major) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.major = major;
    }

    public void addTeacher(Teacher teacher){
        if(this.teachers == null) {
            this.teachers = new ArrayList<>();
        }
        this.teachers.add(teacher);
    }

    public void removeTeacher(Teacher teacher){
        System.out.println("2");
        if(this.teachers != null){
            System.out.println("3");
            this.teachers.remove(teacher);
        }
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", major='" + major + '\'' +
                '}';
    }
}
