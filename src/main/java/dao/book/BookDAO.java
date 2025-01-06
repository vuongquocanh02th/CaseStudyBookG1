package dao.book;

import dbconnect.DBConnection;
import model.Books;
import model.Categories;
import model.Genres;
import model.Publishers;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class BookDAO implements IBookDAO {
    private static final String SELECT_ALL_BOOKS = "SELECT * FROM Books";
    private static final String SELECT_BOOKS_BY_ID = "select * from books where id = ?";
    private static final String UPDATE_BOOK = "UPDATE Books SET bookName = ?, description = ?, status = ?, genId = ?, publisherId = ?, categoryId = ? WHERE id = ?";
    private static final String DELETE_BOOK = "DELETE FROM Books WHERE ID = ?";
    private static final String INSERT_BOOK = "INSERT INTO Books (BookName, Description, Status, GenID, PublisherID, CategoryID) values (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_BOOKS_INFO = "SELECT b.ID, b.BookName, b.Description, b.Status, g.Name AS GenreName, \n" +
            "       p.Name AS PublisherName, c.Name AS CategoryName \n" +
            "FROM Books b \n" +
            "JOIN Genres g ON b.GenID = g.ID \n" +
            "JOIN Publishers p ON b.PublisherID = p.PublisherID \n" +
            "JOIN Categories c ON b.CategoryID = c.CategoryID";
    private static final String SEARCH_BOOKS = "SELECT b.ID, b.BookName, b.Description, b.Status, g.Name AS GenreName, " +
            "p.Name AS PublisherName, c.Name AS CategoryName " +
            "FROM Books b " +
            "JOIN Genres g ON b.GenID = g.ID " +
            "JOIN Publishers p ON b.PublisherID = p.PublisherID " +
            "JOIN Categories c ON b.CategoryID = c.CategoryID " +
            "WHERE p.Name LIKE ? AND g.Name LIKE ?";

    private static final String SELECT_BOOKS_BY_PAGE = "SELECT b.*, g.name AS GenreName, p.name AS PublisherName, c.name AS CategoryName " +
            "FROM Books b " +
            "JOIN Genres g ON b.genreId = g.id " +
            "JOIN Publishers p ON b.publisherId = p.id " +
            "JOIN Categories c ON b.categoryId = c.id " +
            "LIMIT ?, ?";
    private static final String SELECT_BOOK_COUNT = "SELECT COUNT(*) FROM Books";

    @Override
    public List<Books> getAllBooks() {
        List<Books> books = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(SELECT_ALL_BOOKS_INFO);
             ResultSet rs = statement.executeQuery()) {
            while (rs.next()) {
                Books book = new Books();
                book.setId(rs.getInt("ID"));
                book.setBookName(rs.getString("BookName"));
                book.setDescription(rs.getString("Description"));
                book.setStatus(rs.getString("Status"));

                Genres genre = new Genres();
                genre.setName(rs.getString("GenreName"));
                book.setGenre(genre);

                Publishers publisher = new Publishers();
                publisher.setName(rs.getString("PublisherName"));
                book.setPublisher(publisher);

                Categories category = new Categories();
                category.setName(rs.getString("CategoryName"));
                book.setCategory(category);

                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    @Override
    public boolean addBook(Books book) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_BOOK)) {
            ps.setString(1, book.getBookName());
            ps.setString(2, book.getDescription());
            ps.setString(3, book.getStatus());
            ps.setInt(4, book.getGenId());
            ps.setInt(5, book.getPublisherId());
            ps.setInt(6, book.getCategoryId());
            int rowsAffected = ps.executeUpdate(); // Kiểm tra số dòng bị ảnh hưởng
            if (rowsAffected > 0) {
                System.out.println("Book added successfully.");
                return true;
            } else {
                System.out.println("No rows affected.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean updateBook(Books book) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(UPDATE_BOOK)) {
            ps.setString(1, book.getBookName());
            ps.setString(2, book.getDescription());
            ps.setString(3, book.getStatus());
            ps.setInt(4, book.getGenId());
            ps.setInt(5, book.getPublisherId());
            ps.setInt(6, book.getCategoryId());
            ps.setInt(7, book.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteBook(int id) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(DELETE_BOOK)) {
            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Books getBookById(int id) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BOOKS_BY_ID)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Books(
                        rs.getInt("ID"),
                        rs.getString("BookName"),
                        rs.getString("Description"),
                        rs.getString("Status"),
                        rs.getInt("GenID"),
                        rs.getInt("PublisherID"),
                        rs.getInt("CategoryID")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Books> searchBooks(String publisherName, String genreName) {
        List<Books> books = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SEARCH_BOOKS)) {
            ps.setString(1, "%" + publisherName + "%");
            ps.setString(2, "%" + genreName + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setId(rs.getInt("ID"));
                book.setBookName(rs.getString("BookName"));
                book.setDescription(rs.getString("Description"));
                book.setStatus(rs.getString("Status"));

                Genres genre = new Genres();
                genre.setName(rs.getString("GenreName"));
                book.setGenre(genre);

                Publishers publisher = new Publishers();
                publisher.setName(rs.getString("PublisherName"));
                book.setPublisher(publisher);

                Categories category = new Categories();
                category.setName(rs.getString("CategoryName"));
                book.setCategory(category);

                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}
