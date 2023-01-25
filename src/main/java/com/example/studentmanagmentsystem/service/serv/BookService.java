package com.example.studentmanagmentsystem.service.serv;

import com.example.studentmanagmentsystem.entity.Book;
import java.util.List;

public interface BookService {
    List<com.example.studentmanagmentsystem.entity.Book> getAllBooks();
    com.example.studentmanagmentsystem.entity.Book getBookById(Long id);
    void deleteBookById(Long id);
    Book saveBook(Book book);

}
