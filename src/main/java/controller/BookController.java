package controller;

import model.Book;
import model.Category;
import service.book.IBookService;
import service.book.BookServiceImpl;
import service.category.ICategoryService;
import service.category.CategoryServiceImpl;
import utils.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.List;

@WebServlet("/books")
public class BookController extends HttpServlet {
    private final IBookService bookService = new BookServiceImpl();
    private final ICategoryService categoryService = new CategoryServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            default:
                listBooks(request, response);
                break;
        }
    }

    private void listBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = bookService.findAll();
        request.setAttribute("books", books);
        request.getRequestDispatcher("/books/list.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Category> categories = categoryService.findAll();
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/books/add.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book existingBook = bookService.findById(id);
        List<Category> categories = categoryService.findAll();
        request.setAttribute("book", existingBook);
        request.setAttribute("categories", categories);
        request.getRequestDispatcher("/books/edit.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                addBook(request, response);
                break;
            case "edit":
                updateBook(request, response);
                break;
            case "delete":
                deleteBook(request, response);
                break;
        }
    }

    private void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book newBook = createBookFromRequest(request);
        bookService.save(newBook);
        response.sendRedirect("/books");
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String bookName = request.getParameter("bookName");
        String description = request.getParameter("description");
        String status = request.getParameter("status");
    
        // Handle Genre
        String genIDStr = request.getParameter("genID");
        String newGenre = request.getParameter("newGenre");
        Integer genID = null;
        if (newGenre != null && !newGenre.isEmpty()) {
            genID = saveNewGenre(newGenre);
        } else if (genIDStr != null && !genIDStr.isEmpty()) {
            genID = Integer.parseInt(genIDStr);
        }
    
        // Handle Publisher
        String publisherIDStr = request.getParameter("publisherID");
        String newPublisher = request.getParameter("newPublisher");
        Integer publisherID = null;
        if (newPublisher != null && !newPublisher.isEmpty()) {
            publisherID = saveNewPublisher(newPublisher);
        } else if (publisherIDStr != null && !publisherIDStr.isEmpty()) {
            publisherID = Integer.parseInt(publisherIDStr);
        }
    
        // Handle Category
        String categoryIDStr = request.getParameter("categoryID");
        String newCategory = request.getParameter("newCategory");
        Integer categoryID = null;
        if (newCategory != null && !newCategory.isEmpty()) {
            categoryID = saveNewCategory(newCategory);
        } else if (categoryIDStr != null && !categoryIDStr.isEmpty()) {
            categoryID = Integer.parseInt(categoryIDStr);
        }
    
        Book book = new Book(id, bookName, description, status, genID, publisherID, categoryID);
        bookService.update(book);
        response.sendRedirect("/books");
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        bookService.delete(id);
        response.sendRedirect("/books");
    }

    private Book createBookFromRequest(HttpServletRequest request) {
        String bookName = request.getParameter("bookName");
        String description = request.getParameter("description");
        String status = request.getParameter("status");

        Integer genID = getOrCreateId(request, "genID", "newGenre", this::saveNewGenre);
        Integer publisherID = getOrCreateId(request, "publisherID", "newPublisher", this::saveNewPublisher);
        Integer categoryID = getOrCreateId(request, "categoryID", "newCategory", this::saveNewCategory);

        return new Book(bookName, description, status, genID, publisherID, categoryID);
    }

    private Integer getOrCreateId(HttpServletRequest request, String existingIdParam, String newEntityParam, EntitySaver entitySaver) {
        String existingIdStr = request.getParameter(existingIdParam);
        String newEntity = request.getParameter(newEntityParam);
        if (newEntity != null && !newEntity.isEmpty()) {
            return entitySaver.save(newEntity);
        } else if (existingIdStr != null && !existingIdStr.isEmpty()) {
            return Integer.parseInt(existingIdStr);
        }
        return null;
    }

    private Integer saveNewGenre(String newGenre) {
        return saveNewEntity(newGenre, "INSERT INTO Genres (Name) VALUES (?)");
    }

    private Integer saveNewPublisher(String newPublisher) {
        return saveNewEntity(newPublisher, "INSERT INTO Publishers (Name) VALUES (?)");
    }

    private Integer saveNewCategory(String newCategory) {
        return saveNewEntity(newCategory, "INSERT INTO Categories (Name) VALUES (?)");
    }

    private Integer saveNewEntity(String newEntity, String sql) {
        Integer entityId = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, newEntity);
            preparedStatement.executeUpdate();
            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                entityId = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entityId;
    }

    @FunctionalInterface
    private interface EntitySaver {
        Integer save(String entity);
    }
}