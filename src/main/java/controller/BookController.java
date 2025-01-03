package controller;

import model.Book;
import service.book.IBookService;
import service.book.BookServiceImpl;

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
        request.getRequestDispatcher("/books/add.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book existingBook = bookService.findById(id);
        request.setAttribute("book", existingBook);
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
        String genIDStr = request.getParameter("genID");
        String publisherIDStr = request.getParameter("publisherID");
        String categoryIDStr = request.getParameter("categoryID");

        Integer genID = (genIDStr != null && !genIDStr.isEmpty()) ? Integer.parseInt(genIDStr) : null;
        Integer publisherID = (publisherIDStr != null && !publisherIDStr.isEmpty()) ? Integer.parseInt(publisherIDStr) : null;
        Integer categoryID = (categoryIDStr != null && !categoryIDStr.isEmpty()) ? Integer.parseInt(categoryIDStr) : null;

        Book newBook = new Book(bookName, description, status, genID, publisherID, categoryID);
        bookService.save(newBook);
        response.sendRedirect("/books");
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String bookName = request.getParameter("bookName");
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        String genIDStr = request.getParameter("genID");
        String publisherIDStr = request.getParameter("publisherID");
        String categoryIDStr = request.getParameter("categoryID");

        Integer genID = (genIDStr != null && !genIDStr.isEmpty()) ? Integer.parseInt(genIDStr) : null;
        Integer publisherID = (publisherIDStr != null && !publisherIDStr.isEmpty()) ? Integer.parseInt(publisherIDStr) : null;
        Integer categoryID = (categoryIDStr != null && !categoryIDStr.isEmpty()) ? Integer.parseInt(categoryIDStr) : null;

        Book book = new Book(id, bookName, description, status, genID, publisherID, categoryID);
        bookService.update(book);
        response.sendRedirect("/books");
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        bookService.delete(id);
        response.sendRedirect("/books");
    }
}