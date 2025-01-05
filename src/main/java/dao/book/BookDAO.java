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
    private static final String SELECT_ALL_BOOKS_INFO = "SELECT b.ID, b.BookName, b.Description, b.Status, g.Name as GenreName, p.Name as PublisherName, c.Name as CategoryName FROM Books b JOIN Genres g ON b.GenID = g.ID JOIN Publishers p ON b.PublisherID = p.PublisherID JOIN Categories c ON b.CategoryID = c.CategoryID";
    private static final String SEARCH_BOOKS = "SELECT b.ID, b.BookName, b.Description, b.Status, g.Name as GenreName, p.Name as PublisherName, c.Name as CategoryName " +
            "FROM Books b " +
            "JOIN Genres g ON b.GenID = g.ID " +
            "JOIN Publishers p ON b.PublisherID = p.PublisherID " +
            "JOIN Categories c ON b.CategoryID = c.CategoryID " +
            "WHERE p.Name LIKE ? AND g.Name LIKE ?";

    private static final String SELECT_NEWEST_BOOKS = "SELECT * FROM Books ORDER BY    Description" + " DESC LIMIT 10";

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
                // Tạo đối tượng Genres, Publishers, Categories và gán vào book
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
    //phan trang
    private int noOfRecords;

    public List<Books> getBooksByPage(int offset, int noOfRecords) {
        List<Books> books = new ArrayList<>();
        String query = "SELECT SQL_CALC_FOUND_ROWS * FROM Books LIMIT ?, ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, offset);
            ps.setInt(2, noOfRecords);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Books book = new Books();
                book.setId(rs.getInt("ID"));
                book.setBookName(rs.getString("BookName"));
                book.setDescription(rs.getString("Description"));
                book.setStatus(rs.getString("Status"));
                // Set other fields...
                books.add(book);
            }
            rs.close();
            rs = ps.executeQuery("SELECT FOUND_ROWS()");
            if (rs.next()) {
                this.noOfRecords = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public int getNoOfRecords() {
        return noOfRecords;
    }

    //10 cuon sach moi nhat
    public List<Books> getNewestBooks() {
        List<Books> books = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_NEWEST_BOOKS);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Books book = new Books();
                book.setId(rs.getInt("ID"));
                book.setBookName(rs.getString("BookName"));
                book.setDescription(rs.getString("Description"));
                book.setStatus(rs.getString("Status"));
                // Set other fields...
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
}
