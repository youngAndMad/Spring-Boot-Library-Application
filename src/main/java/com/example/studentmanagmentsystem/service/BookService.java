package com.example.studentmanagmentsystem.service;

import com.example.studentmanagmentsystem.entity.Book;
import com.example.studentmanagmentsystem.repository.BookRepository;
import com.example.studentmanagmentsystem.util.Enrichment;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class BookService {

    private BookRepository bookRepository;
    private final Enrichment enrichment;

    public BookService(BookRepository bookRepository, Enrichment enrichment) {
        this.bookRepository = bookRepository;
        this.enrichment = enrichment;
    }

    public List<Book> getAllBooks() {
        return bookRepository
                .findAll().
                stream().
                filter(Book::isActive).
                filter(book -> book.getQuantity() >= 0)
                .collect(Collectors.toList());
    }


    public Book getBookById(Long id) {
        return bookRepository.findById(id).get();
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }
    public void updateBook(Book book){
        Book bookToSave = getBookById(book.getId());
        enrichment.enrichBook(book,bookToSave);
        saveBook(bookToSave);
    }

    public Book getByTitle(String title) {
        return bookRepository.findByTitle(title).orElse(null);
    }

    public void incrementQuantity(Long bookId) {
        Book book = getBookById(bookId);
        book.setQuantity(book.getQuantity() + 1);
        saveBook(book);
    }

    public void decrementQuantity(Book book){
        book.setQuantity(book.getQuantity()-1);
        saveBook(book);
    }
}
