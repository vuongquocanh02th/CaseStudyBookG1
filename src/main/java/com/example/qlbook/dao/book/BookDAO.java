package com.example.qlbook.dao.book;

import com.example.qlbook.model.Book;
import com.example.qlbook.utils.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BookDAO implements IBookDAO {
    private static final String INSERT_BOOK_SQL = "insert into book (id,name,description,imageURL,status,author_id,genre_id) values (?,?,?,?,?,?,?)";
    private static final String SELECT_BOOK_SQL = "select * from book where id=?";
    private static final String SELECT_ALL_BOOK_SQL = "select * from book";
    private static final String UPDATE_BOOK = "update book set name=? where id=? ";
    private static final String DELETE_BOOK = "delete from book where id=?";

    public BookDAO() {

    }

    public void insertBook(Book book) throws SQLException {
        System.out.println(INSERT_BOOK_SQL);
        try(Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_BOOK_SQL)) {
            statement.setInt(1, book.getId());
            statement.setString(2, book.getName());
            statement.setString(3,book.getDescriptiton());
            statement.setString(4, book.getImageUrl());
            statement.setBoolean(5, book.isStatus());
            statement.setInt(6, book.getAuthor_id());
            statement.setInt(7, book.getGenre_id());
            System.out.println(statement);
            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public List<Book> selectAllBook() throws SQLException {
        List<Book> books = null;
        try(Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL_BOOK_SQL)) {
                System.out.println(statement);
                ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String descriptiton = rs.getString("descriptiton");
                String imageUrl = rs.getString("imageURL");
                boolean status = rs.getBoolean("status");
                int author_id = rs.getInt("author_id");
                int genre_id = rs.getInt("genre_id");
                Book book = new Book(id, name, descriptiton, imageUrl, status, author_id, genre_id);

            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public Book selectBookById(int id) throws SQLException {
        Book book = null;
        try(Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BOOK_SQL)) {
            statement.setInt(1, id);
            System.out.println(statement);
            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                int bookId = rs.getInt("id");
                String name = rs.getString("name");
                String descriptiton = rs.getString("descriptiton");
                String imageUrl = rs.getString("imageURL");
                boolean status = rs.getBoolean("status");
                int author_id = rs.getInt("author_id");
                int genre_id = rs.getInt("genre_id");
                Book newBook = new Book(bookId, name, descriptiton, imageUrl, status, author_id, genre_id);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    public boolean updateBook(Book book) throws SQLException {
        boolean rowUpdated = false;
        try(Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_BOOK)) {
            statement.setInt(1, book.getId());
            statement.setString(2, book.getName());
            statement.setString(3, book.getDescriptiton());
            statement.setString(4, book.getImageUrl());
            statement.setBoolean(5, book.isStatus());
            statement.setInt(6, book.getAuthor_id());
            statement.setInt(7, book.getGenre_id());
            System.out.println(statement);
            statement.executeUpdate();
            rowUpdated = statement.executeUpdate() > 0;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    public boolean deleteBook(int id) throws SQLException{
        boolean rowDelete = false;
        try(Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_BOOK)) {
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
