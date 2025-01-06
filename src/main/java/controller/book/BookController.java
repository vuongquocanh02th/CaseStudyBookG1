package controller.book;

import dao.book.BookDAO;
import dao.book.IBookDAO;
import dao.category.CategoryDAO;
import dao.category.ICategoryDAO;
import dao.genre.GenreDAO;
import dao.genre.IGenreDAO;
import dao.publisher.IPublisherDAO;
import dao.publisher.PublisherDAO;
import dbconnect.DBConnection;
import model.Books;
import model.Categories;
import model.Genres;
import model.Publishers;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/books")
public class BookController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private final BookDAO bookDAO = new BookDAO();
    private final GenreDAO genreDAO = new GenreDAO();
    private final PublisherDAO publisherDAO = new PublisherDAO();
    private final CategoryDAO categoryDAO = new CategoryDAO();

    // Xử lý yêu cầu GET và chuyển dữ liệu tới JSP
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null || action.equals("listBooks")) {
             loadBook(request, response);

        }
        switch (action) {
            case "addBook":
                showAddBookForm(request, response);
                break;
            case "editBook":
                showEditBookForm(request, response);
                break;
            case "deleteBook":
                showDeleteBookForm(request, response);
                break;
            case "searchBooks":
                searchBooks(request, response);
                break;
        }
    }

    private void searchBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String publisherName = request.getParameter("publisherName");
        String genreName = request.getParameter("genreName");
        List<Books> booksList = bookDAO.searchBooks(publisherName, genreName);
        request.setAttribute("books", booksList);
        request.setAttribute("publishers", publisherDAO.getAllPublishers());
        request.setAttribute("genres", genreDAO.getAllGenres());
        request.getRequestDispatcher("book/book.jsp").forward(request, response);
    }

    private void showDeleteBookForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Books book = bookDAO.getBookById(id);
        request.setAttribute("book", book);
        request.getRequestDispatcher("book/deleteBook.jsp").forward(request, response);
    }

    private void showEditBookForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Books book = bookDAO.getBookById(id);
        List<Genres> genres = genreDAO.getAllGenres();
        List<Publishers> publishers = publisherDAO.getAllPublishers();
        List<Categories> categories = categoryDAO.getAllCategories();

        request.setAttribute("book", book);
        request.setAttribute("genres", genres);
        request.setAttribute("publishers", publishers);
        request.setAttribute("categories", categories);

        request.getRequestDispatcher("book/editBook.jsp").forward(request, response);
    }

    private void loadBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Books> booksList = bookDAO.getAllBooks();
        request.setAttribute("books", booksList);
        request.setAttribute("publishers", publisherDAO.getAllPublishers());
        request.setAttribute("genres", genreDAO.getAllGenres());
        request.getRequestDispatcher("book/book.jsp").forward(request, response);
    }


    private void showAddBookForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Genres> genres = genreDAO.getAllGenres();
        List<Publishers> publishers = publisherDAO.getAllPublishers();
        List<Categories> categories = categoryDAO.getAllCategories();

        request.setAttribute("genres", genres);
        request.setAttribute("publishers", publishers);
        request.setAttribute("categories", categories);

        request.getRequestDispatcher("book/addBook.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("addBook".equals(action)) {
            addNewBook(req, resp);
        }else if ("updateBook".equals(action)) {
            updateBook(req, resp);
        } else if ("confirmDeleteBook".equals(action)) {
            deleteBook(req, resp);
        }
    }

    private void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        boolean isDeleted = bookDAO.deleteBook(id);
        if (isDeleted) {
            req.setAttribute("message", "Book deleted successfully.");
        } else {
            req.setAttribute("message", "Failed to delete book.");
        }
        loadBook(req, resp);
    }

    private void updateBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String bookName = req.getParameter("bookName");
        String description = req.getParameter("description");
        String status = req.getParameter("status");
        int genId = Integer.parseInt(req.getParameter("genId"));
        int publisherId = Integer.parseInt(req.getParameter("publisherId"));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));

        Books book = new Books(id, bookName, description, status, genId, publisherId, categoryId);

        boolean isUpdated = bookDAO.updateBook(book);
        if (isUpdated) {
            req.setAttribute("message", "Book updated successfully.");
        } else {
            req.setAttribute("message", "Failed to update book.");
        }
        List<Genres> genres = genreDAO.getAllGenres();
        List<Publishers> publishers = publisherDAO.getAllPublishers();
        List<Categories> categories = categoryDAO.getAllCategories();

        req.setAttribute("book", book);
        req.setAttribute("genres", genres);
        req.setAttribute("publishers", publishers);
        req.setAttribute("categories", categories);

        req.getRequestDispatcher("book/editBook.jsp").forward(req, resp);
    }

    private void addNewBook(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String bookName = req.getParameter("bookName");
        String description = req.getParameter("description");
        String status = req.getParameter("status");
        int genId = Integer.parseInt(req.getParameter("genId"));
        int publisherId = Integer.parseInt(req.getParameter("publisherId"));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));

        Books book = new Books(0, bookName, description, status, genId, publisherId, categoryId);

        boolean isAdded = bookDAO.addBook(book);
        if (isAdded) {
            req.setAttribute("message", "Book added successfully.");
        } else {
            req.setAttribute("message", "Failed to add book.");
        }
        List<Genres> genres = genreDAO.getAllGenres();
        List<Publishers> publishers = publisherDAO.getAllPublishers();
        List<Categories> categories = categoryDAO.getAllCategories();

        req.setAttribute("genres", genres);
        req.setAttribute("publishers", publishers);
        req.setAttribute("categories", categories);

        req.getRequestDispatcher("book/addBook.jsp").forward(req, resp);
    }
}
