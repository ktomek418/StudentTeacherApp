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
public class Teacher {

    @Id
    @GeneratedValue
    private int id_teacher;
    @Size(min = 3,message = "Name must be longer than 2 characters")
    private String firstName;
    private String lastName;
    @Min(value = 18)
    private int age;
    @Email(message="Please provide a valid email address")
    private String email;
    private String subject;

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.DETACH})
    @JoinTable(
            name = "students_teachers",
            joinColumns = @JoinColumn(name = "id_teacher"),
            inverseJoinColumns = @JoinColumn(name = "id_student")
    )
    private List<Student> students;

    public Teacher(String firstName, String lastName, int age, String email, String subject) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.email = email;
        this.subject = subject;
    }

    public void addStudent(Student student){
        if(this.students == null) {
            this.students = new ArrayList<>();
        }
        this.students.add(student);
    }

    public void removeStudent(Student student){
        if(this.students != null){
            this.students.remove(student);
        }
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
