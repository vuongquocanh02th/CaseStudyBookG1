package com.example.qlbook.dao.author;

import com.example.qlbook.model.Author;
import com.example.qlbook.utils.DataBaseConnection;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthorDAO {
    private static final String INSERT_AUTHOR = "insert into author(author_id,author_name) values(?,?)";
    private static final String SELECT_AUTHOR = "select * from author where author_id = ?";
    private static final String SELECT_ALL_AUTHOR = "select * from author";
    private static final String UPDATE_AUTHOR = "update author set author_name=? where author_id = ?";
    private static final String DELETE_AUTHOR = "delete from author where author_id = ?";


    public AuthorDAO() {
    }

    public void insertAuthor(Author author) throws SQLException {
        System.out.println(INSERT_AUTHOR);
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_AUTHOR)) {
            statement.setInt(1, author.getId());
            statement.setString(2, author.getName());
            System.out.println(statement);
            statement.executeUpdate();

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Author selectAuthorById(int id) throws SQLException {
        Author author = null;
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_AUTHOR)) {
            statement.setInt(1, id);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int author_id = rs.getInt("author_id");
                String name = rs.getString("author_name");
                Author newAuthor = new Author(name, author_id);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return author;
    }

    public List<Author> selectAllAuthor() throws SQLException {
        List<Author> authorList = null;
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL_AUTHOR)) {
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int author_id = rs.getInt("author_id");
                String name = rs.getString("author_name");
                Author newAuthor = new Author(name, author_id);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return authorList;
    }

 public boolean deleteAuthor(int id) throws SQLException {
        boolean deleteRow = false;
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_AUTHOR)) {
            statement.setInt(1, id);
            deleteRow = statement.executeUpdate() > 0;
        }
        return deleteRow;
 }

 public boolean updateAuthor(Author author) throws SQLException {
        boolean updateRow = false;
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_AUTHOR)) {
            statement.setString(1, author.getName());
            statement.setInt(2, author.getId());
            System.out.println(statement);
            statement.executeUpdate();
            updateRow = statement.executeUpdate() > 0;

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return updateRow;
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
