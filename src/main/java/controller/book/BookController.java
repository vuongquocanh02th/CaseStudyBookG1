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
import model.Genres;

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

    // Xử lý yêu cầu GET và chuyển dữ liệu tới JSP
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        BookDAO bookDAO = new BookDAO();
        List<Books> booksList = bookDAO.getAllBooks();

        request.setAttribute("books", booksList);
        request.getRequestDispatcher("book/book.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookName = req.getParameter("bookName");
        String description = req.getParameter("description");
        String status = req.getParameter("status");
        int genId = Integer.parseInt(req.getParameter("genId"));
        int publisherId = Integer.parseInt(req.getParameter("publisherId"));
        int categoryId = Integer.parseInt(req.getParameter("categoryId"));

        Books book = new Books(0, bookName, description, status, genId, publisherId, categoryId);
        //bookDAO.addBook(book);
        resp.sendRedirect("books");
    }
}
