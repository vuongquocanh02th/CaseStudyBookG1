package com.example.qlbook.dao.genre;

import com.example.qlbook.model.Genre;
import com.example.qlbook.utils.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class GenreDAO implements IGenreDAO{
    private static final String INSERT_GENRE = "insert into genre(id,name) values(?,?)";
    private static final String SELECT_GENRE = "select * from genre where id = ?";
    private static final String SELECT_ALL_GENRE = "select * from genre";
    private static final String UPDATE_GENRE = "update genre set name=? where id = ?";
    private static final String DELETE_GENRE = "delete from genre where id = ?";

    public GenreDAO() {
    }

    public void insertGenre(Genre genre) throws SQLException {
        System.out.println(INSERT_GENRE);
        try(Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(INSERT_GENRE)) {
            statement.setInt(1, genre.getId());
            statement.setString(2, genre.getName());
            System.out.println(statement);
            statement.executeUpdate();

        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    public Genre selectGenreById(int id) throws SQLException {
        Genre genre = null;
        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_GENRE)) {
            statement.setInt(1, id);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();

            while(rs.next()) {
                int genre_id = rs.getInt("id");
                String name = rs.getString("name");
                Genre newGenre = new Genre(name,genre_id);

            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return genre;
    }

    public List<Genre> selectAllGenre() throws SQLException {
        List<Genre> genreList = null;
        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(SELECT_ALL_GENRE)) {
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                Genre newGenre = new Genre(name,id);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return genreList;
    }

    public boolean updateGenre(Genre genre) throws SQLException {
        boolean rowUpdated = false;
        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_GENRE)) {
            statement.setInt(1, genre.getId());
            statement.setString(2, genre.getName());
            System.out.println(statement);
            statement.executeUpdate();
            rowUpdated = statement.executeUpdate() > 0;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    public boolean deleteGenre(int id) throws SQLException {
        boolean rowDelete;
        try (Connection connection = DataBaseConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_GENRE)) {
            statement.setInt(1, id);
            rowDelete = statement.executeUpdate() > 0;
        }
        return rowDelete;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + ((SQLException) e).getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
