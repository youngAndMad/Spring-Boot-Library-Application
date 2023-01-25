package com.example.studentmanagmentsystem.service.Mappers;

import com.example.studentmanagmentsystem.entity.Book;
import org.springframework.jdbc.core.RowMapper;

import javax.swing.tree.TreePath;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookMapper implements RowMapper<Book> {

    @Override
    public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
        Book book = new Book();

        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));
        book.setYear(rs.getInt("year"));
        book.setDescription(rs.getString("description"));
        book.setGenre(rs.getString("genre"));
        return null;
    }
}
