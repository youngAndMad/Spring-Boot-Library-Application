package com.example.studentmanagmentsystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "orders")
public class Connector {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_id")
    private int studentId;

    @Column(name = "book_id")
    private int bookId;

    public Connector(int studentId, int bookId) {
        this.studentId = studentId;
        this.bookId = bookId;
    }

    public Connector() {}

    public Long getId() {
        return id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }
}
