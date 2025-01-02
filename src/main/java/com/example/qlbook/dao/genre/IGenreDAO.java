package com.example.qlbook.dao.genre;

import com.example.qlbook.model.Genre;

import java.sql.SQLException;
import java.util.List;

public interface IGenreDAO {
   public void insertGenre(Genre genre) throws SQLException;
   public boolean updateGenre(Genre genre) throws SQLException;
   public boolean deleteGenre(int id) throws SQLException;
   public Genre selectGenreById(int id) throws SQLException;
    public List<Genre> selectAllGenre() throws SQLException;
}




