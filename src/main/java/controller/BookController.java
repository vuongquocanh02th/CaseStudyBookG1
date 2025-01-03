package controller;

import model.Book;
import model.Category;
import service.book.IBookService;
import service.book.BookServiceImpl;
import service.category.ICategoryService;
import service.category.CategoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/books")
public class BookController extends HttpServlet {
    private IBookService bookService = new BookServiceImpl();
    private ICategoryService categoryService = new CategoryServiceImpl();

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
        String bookName = request.getParameter("bookName");
        String description = request.getParameter("description");
        String status = request.getParameter("status");

        // Handle Genre
        String genIDStr = request.getParameter("genID");
        String newGenre = request.getParameter("newGenre");
        Integer genID = null;
        if (newGenre != null && !newGenre.isEmpty()) {
            // Save new genre and get its ID
            // Assuming you have a method to save the genre and get its ID
            genID = saveNewGenre(newGenre);
        } else if (genIDStr != null && !genIDStr.isEmpty()) {
            genID = Integer.parseInt(genIDStr);
        }

        // Handle Publisher
        String publisherIDStr = request.getParameter("publisherID");
        String newPublisher = request.getParameter("newPublisher");
        Integer publisherID = null;
        if (newPublisher != null && !newPublisher.isEmpty()) {
            // Save new publisher and get its ID
            // Assuming you have a method to save the publisher and get its ID
            publisherID = saveNewPublisher(newPublisher);
        } else if (publisherIDStr != null && !publisherIDStr.isEmpty()) {
            publisherID = Integer.parseInt(publisherIDStr);
        }

        // Handle Category
        String categoryIDStr = request.getParameter("categoryID");
        String newCategory = request.getParameter("newCategory");
        Integer categoryID = null;
        if (newCategory != null && !newCategory.isEmpty()) {
            // Save new category and get its ID
            // Assuming you have a method to save the category and get its ID
            categoryID = saveNewCategory(newCategory);
        } else if (categoryIDStr != null && !categoryIDStr.isEmpty()) {
            categoryID = Integer.parseInt(categoryIDStr);
        }

        Book newBook = new Book(bookName, description, status, genID, publisherID, categoryID);
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
            // Save new genre and get its ID
            // Assuming you have a method to save the genre and get its ID
            genID = saveNewGenre(newGenre);
        } else if (genIDStr != null && !genIDStr.isEmpty()) {
            genID = Integer.parseInt(genIDStr);
        }

        // Handle Publisher
        String publisherIDStr = request.getParameter("publisherID");
        String newPublisher = request.getParameter("newPublisher");
        Integer publisherID = null;
        if (newPublisher != null && !newPublisher.isEmpty()) {
            // Save new publisher and get its ID
            // Assuming you have a method to save the publisher and get its ID
            publisherID = saveNewPublisher(newPublisher);
        } else if (publisherIDStr != null && !publisherIDStr.isEmpty()) {
            publisherID = Integer.parseInt(publisherIDStr);
        }

        // Handle Category
        String categoryIDStr = request.getParameter("categoryID");
        String newCategory = request.getParameter("newCategory");
        Integer categoryID = null;
        if (newCategory != null && !newCategory.isEmpty()) {
            // Save new category and get its ID
            // Assuming you have a method to save the category and get its ID
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

    // Dummy methods to save new genre, publisher, and category
    // Replace these with actual implementations
    private Integer saveNewGenre(String newGenre) {
        // Save new genre and return its ID
        return 1; // Replace with actual implementation
    }

    private Integer saveNewPublisher(String newPublisher) {
        // Save new publisher and return its ID
        return 1; // Replace with actual implementation
    }

    private Integer saveNewCategory(String newCategory) {
        // Save new category and return its ID
        return 1; // Replace with actual implementation
    }
}