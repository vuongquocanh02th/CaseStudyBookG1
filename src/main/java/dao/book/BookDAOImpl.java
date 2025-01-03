package dao.book;

import model.Book;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements IBookDAO {
    private static final String SELECT_ALL_BOOKS = "SELECT * FROM Books";
    private static final String SELECT_BOOK_BY_ID = "SELECT * FROM Books WHERE ID = ?";
    private static final String INSERT_BOOK = "INSERT INTO Books (BookName, Description, Status, GenID, PublisherID, CategoryID) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_BOOK = "UPDATE Books SET BookName = ?, Description = ?, Status = ?, GenID = ?, PublisherID = ?, CategoryID = ? WHERE ID = ?";
    private static final String DELETE_BOOK = "DELETE FROM Books WHERE ID = ?";

    @Override
    public List<Book> findAll() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BOOKS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String bookName = rs.getString("BookName");
                String description = rs.getString("Description");
                String status = rs.getString("Status");
                Integer genID = rs.getObject("GenID") != null ? rs.getInt("GenID") : null;
                Integer publisherID = rs.getObject("PublisherID") != null ? rs.getInt("PublisherID") : null;
                Integer categoryID = rs.getObject("CategoryID") != null ? rs.getInt("CategoryID") : null;
                books.add(new Book(id, bookName, description, status, genID, publisherID, categoryID));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public Book findById(int id) {
        Book book = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BOOK_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String bookName = rs.getString("BookName");
                String description = rs.getString("Description");
                String status = rs.getString("Status");
                Integer genID = rs.getObject("GenID") != null ? rs.getInt("GenID") : null;
                Integer publisherID = rs.getObject("PublisherID") != null ? rs.getInt("PublisherID") : null;
                Integer categoryID = rs.getObject("CategoryID") != null ? rs.getInt("CategoryID") : null;
                book = new Book(id, bookName, description, status, genID, publisherID, categoryID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    @Override
    public void save(Book book) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BOOK)) {
            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setString(2, book.getDescription());
            preparedStatement.setString(3, book.getStatus());
            if (book.getGenID() != null) {
                preparedStatement.setInt(4, book.getGenID());
            } else {
                preparedStatement.setNull(4, Types.INTEGER);
            }
            if (book.getPublisherID() != null) {
                preparedStatement.setInt(5, book.getPublisherID());
            } else {
                preparedStatement.setNull(5, Types.INTEGER);
            }
            if (book.getCategoryID() != null) {
                preparedStatement.setInt(6, book.getCategoryID());
            } else {
                preparedStatement.setNull(6, Types.INTEGER);
            }
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Book book) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BOOK)) {
            preparedStatement.setString(1, book.getBookName());
            preparedStatement.setString(2, book.getDescription());
            preparedStatement.setString(3, book.getStatus());
            if (book.getGenID() != null) {
                preparedStatement.setInt(4, book.getGenID());
            } else {
                preparedStatement.setNull(4, Types.INTEGER);
            }
            if (book.getPublisherID() != null) {
                preparedStatement.setInt(5, book.getPublisherID());
            } else {
                preparedStatement.setNull(5, Types.INTEGER);
            }
            if (book.getCategoryID() != null) {
                preparedStatement.setInt(6, book.getCategoryID());
            } else {
                preparedStatement.setNull(6, Types.INTEGER);
            }
            preparedStatement.setInt(7, book.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BOOK)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}