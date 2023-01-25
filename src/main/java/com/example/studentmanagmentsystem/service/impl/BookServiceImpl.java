package com.example.studentmanagmentsystem.service.impl;

import com.example.studentmanagmentsystem.repository.BookRepository;
import com.example.studentmanagmentsystem.service.serv.BookService;
import com.example.studentmanagmentsystem.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        super();
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    @Override
    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }


}
