package com.example.qlbook.dao.book;

import com.example.qlbook.model.Book;

import java.sql.SQLException;
import java.util.List;

public interface IBookDAO {
    public void insertBook(Book book) throws SQLException;
    public boolean updateBook(Book book) throws SQLException;
    public boolean deleteBook(int id) throws SQLException;
    public Book selectBookById(int id) throws SQLException;
    public List<Book> selectAllBook() throws SQLException;
}
