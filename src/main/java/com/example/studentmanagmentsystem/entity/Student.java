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

    @Email
    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "gender" , nullable = false)
    private String gender;

    @Min(value = 0 , message = "age should be greater than 0")
    @Column(name = "age" , nullable = false)
    private int age;

    public Student(String firstName, String lastName, String email , String gender , int age) {
        this.firstName = firstName;
        this.age = age;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }

    public Student() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getGender(){
        return gender;
    }
    public void setGender(String gender){
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
}