package controller.borrow;

import dao.book.BookDAO;
import dao.book.IBookDAO;
import dao.borrow.BorrowDAO;
import dao.borrow.IBorrowDAO;
import model.Borrow;
import model.Books;
import service.borrow.BorrowService;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/borrow")
public class BorrowController extends HttpServlet {
    private BorrowService borrowService = new BorrowService(new BorrowDAO());
    private IBookDAO bookDAO;

    public void init(){
        bookDAO = new BookDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if ("manageBorrows".equals(action) && "admin".equals(role)) {
            List<Borrow> borrows = borrowService.getAllBorrows();
            request.setAttribute("borrows", borrows);
            request.getRequestDispatcher("borrow/manageBorrows.jsp").forward(request, response);
        } else if ("listBorrows".equals(action) && "user".equals(role)) {
            List<Books> books = bookDAO.getAllBooks();
            request.setAttribute("books", books);
            request.getRequestDispatcher("borrow/borrowBooks.jsp").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if ("borrowBooks".equals(action) && "user".equals(role)) {
            try {
                int bookId = Integer.parseInt(request.getParameter("bookId"));
                int customerId = (int) session.getAttribute("customerId"); // Assuming customerId is stored in session

                // Get the current date for borrowDate
                Date borrowDate = new Date();

                // Assuming a fixed return date period of 14 days for simplicity
                Date returnDate = new Date(borrowDate.getTime() + (14L * 24 * 60 * 60 * 1000));

                boolean success = borrowService.borrowBook(customerId, bookId, borrowDate, returnDate);
                if (success) {
                    response.sendRedirect(request.getContextPath() + "/borrow?action=listBorrows");
                } else {
                    response.sendRedirect(request.getContextPath() + "/error.jsp");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.sendRedirect(request.getContextPath() + "/error.jsp");
            }
        } else {
            response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
        }
    }
}