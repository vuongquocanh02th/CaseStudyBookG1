package com.example.qlbook.dao.author;

import com.example.qlbook.model.Author;
import com.example.qlbook.model.Genre;

import java.sql.SQLException;
import java.util.List;

public interface IAuthorDAO {
    public void insertAuthor(Author author) throws SQLException;
    public boolean updateAuthor(Author author) throws SQLException;
    public boolean deleteAuthor(int id) throws SQLException;
    public Author selectAuthorById(int id) throws SQLException;
    public List<Author> selectAllAuthor() throws SQLException;
}
