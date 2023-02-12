package com.example.studentmanagmentsystem.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;


@Entity
@Table(name = "students")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "first name should not be empty")
    @Column(name = "first_name", nullable = false)
    private String firstName;

    @NotEmpty(message = "last name should not be empty")
    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Email(message = "invalid login")
    @Column(name = "email", nullable = false)
    @NotEmpty(message = "email should not be empty")
    private String email;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Min(value = 0, message = "age should be greater than 0")
    @Column(name = "age")
    private int age;

    @Size(min = 8 , message = "password should be longest than 8 characters")
    private String password;


    public Student(String firstName, String lastName, String email, String gender, int age, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
        this.age = age;
        this.password = password;
    }

    public Student() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
