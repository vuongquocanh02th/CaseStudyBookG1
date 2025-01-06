package dao.book;

import model.Book;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements IBookDAO {
    private static final String SELECT_ALL_BOOKS = "SELECT b.ID, b.BookName, b.Description, b.Status, g.Name AS GenreName, p.Name AS PublisherName, c.Name AS CategoryName " +
            "FROM Books b " +
            "LEFT JOIN Genres g ON b.GenID = g.ID " +
            "LEFT JOIN Publishers p ON b.PublisherID = p.PublisherID " +
            "LEFT JOIN Categories c ON b.CategoryID = c.CategoryID";
    private static final String SELECT_BOOK_BY_ID = "SELECT b.ID, b.BookName, b.Description, b.Status, g.Name AS GenreName, p.Name AS PublisherName, c.Name AS CategoryName " +
            "FROM Books b " +
            "LEFT JOIN Genres g ON b.GenID = g.ID " +
            "LEFT JOIN Publishers p ON b.PublisherID = p.PublisherID " +
            "LEFT JOIN Categories c ON b.CategoryID = c.CategoryID " +
            "WHERE b.ID = ?";
    private static final String INSERT_BOOK = "INSERT INTO Books (BookName, Description, Status, GenID, PublisherID, CategoryID) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String UPDATE_BOOK = "UPDATE Books SET BookName = ?, Description = ?, Status = ?, GenID = ?, PublisherID = ?, CategoryID = ? WHERE ID = ?";
    private static final String DELETE_BOOK = "DELETE FROM Books WHERE ID = ?";
    private static final String INSERT_GENRE = "INSERT INTO Genres (Name) VALUES (?)";
    private static final String INSERT_PUBLISHER = "INSERT INTO Publishers (Name) VALUES (?)";
    private static final String INSERT_CATEGORY = "INSERT INTO Categories (Name) VALUES (?)";

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
                String genreName = rs.getString("GenreName");
                String publisherName = rs.getString("PublisherName");
                String categoryName = rs.getString("CategoryName");
                books.add(new Book(id, bookName, description, status, genreName, publisherName, categoryName));
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
                String genreName = rs.getString("GenreName");
                String publisherName = rs.getString("PublisherName");
                String categoryName = rs.getString("CategoryName");
                book = new Book(id, bookName, description, status, genreName, publisherName, categoryName);
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
            preparedStatement.setObject(4, book.getGenID(), Types.INTEGER);
            preparedStatement.setObject(5, book.getPublisherID(), Types.INTEGER);
            preparedStatement.setObject(6, book.getCategoryID(), Types.INTEGER);
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
        preparedStatement.setObject(4, book.getGenID(), Types.INTEGER);
        preparedStatement.setObject(5, book.getPublisherID(), Types.INTEGER);
        preparedStatement.setObject(6, book.getCategoryID(), Types.INTEGER);
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

    public int saveNewGenre(String newGenre) {
        int genreId = -1;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_GENRE, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, newGenre);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                genreId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genreId;
    }

    public int saveNewPublisher(String newPublisher) {
        int publisherId = -1;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PUBLISHER, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, newPublisher);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                publisherId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publisherId;
    }

    public int saveNewCategory(String newCategory) {
        int categoryId = -1;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, newCategory);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                categoryId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoryId;
    }
}