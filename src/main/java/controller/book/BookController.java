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

        }
    }

    private void loadBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Books> booksList = bookDAO.getAllBooks();
        request.setAttribute("books", booksList);
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
        }
    }

    private void addNewBook(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String bookName = req.getParameter("bookName");
        String description = req.getParameter("description");
        String status = req.getParameter("status");
        int genId = Integer.parseInt(req.getParameter("genId"));
        int publisherId = Integer.parseInt(req.getParameter("publisherId"));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));

        // Log tham số để kiểm tra
        System.out.println("Book Name: " + bookName);
        System.out.println("Description: " + description);
        System.out.println("Status: " + status);
        System.out.println("Genre ID: " + genId);
        System.out.println("Publisher ID: " + publisherId);
        System.out.println("Category ID: " + categoryId);

        Books book = new Books(0, bookName, description, status, genId, publisherId, categoryId);

        boolean isAdded = bookDAO.addBook(book);
        if (isAdded) {
            System.out.println("Book added successfully.");
        } else {
            System.out.println("Failed to add book.");
        }

        resp.sendRedirect("books");
    }
}
