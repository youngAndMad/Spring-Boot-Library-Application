package com.example.studentmanagmentsystem.util;

import com.example.studentmanagmentsystem.entity.Book;
import com.example.studentmanagmentsystem.entity.Student;
import org.springframework.stereotype.Component;

@Component
public class Enrichment {

    public void enrichBook(Book book, Book bookToSave) {
        bookToSave.setTitle(book.getTitle());
        bookToSave.setQuantity(book.getQuantity());
        bookToSave.setGenre(book.getGenre());
        bookToSave.setDescription(book.getDescription());
        bookToSave.setId(book.getId());
        bookToSave.setYear(book.getYear());
        bookToSave.setAuthor(book.getAuthor());
    }
    public void enrichStudent(Student student , Student studentToSave){
        studentToSave.setId(student.getId());
        studentToSave.setAge(student.getAge());
        studentToSave.setGender(student.getGender());
        studentToSave.setFirstName(student.getFirstName());
        studentToSave.setLastName(student.getLastName());
        studentToSave.setEmail(student.getEmail());
    }
}
