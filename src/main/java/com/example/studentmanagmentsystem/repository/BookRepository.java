package com.example.studentmanagmentsystem.repository;

import com.example.studentmanagmentsystem.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository<Book, Long> {
}
