package com.example.studentmanagmentsystem.service;

import com.example.studentmanagmentsystem.repository.BookRepository;
import com.example.studentmanagmentsystem.entity.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookService {

    private BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll().stream().filter(Book::isActive).filter(book -> book.getQuantity()>0).collect(Collectors.toList());
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    public void deleteBookById(Long id) {
        bookRepository.deleteById(id);
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }


    public Book getByTitle(String title){
        return bookRepository.findByTitle(title).orElse(null);
    }

    public void incrementQuantity(Long bookId) {
        Book book = getBookById(bookId);
        book.setQuantity(book.getQuantity() + 1);
       saveBook(book);
    }

}
