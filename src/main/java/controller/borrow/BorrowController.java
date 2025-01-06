package controller.borrow;

import dao.book.BookDAO;
import dao.book.IBookDAO;
import dao.borrow.BorrowDAO;
import dao.borrow.IBorrowDAO;
import dao.customer.CustomerDAO;
import dao.customer.ICustomerDAO;
import model.BorrowDetail;
import model.Books;
import model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet("/borrow")
public class BorrowController extends HttpServlet {
    private IBorrowDAO borrowDAO;
    private IBookDAO bookDAO;
    private ICustomerDAO customerDAO;

    public void init() {
        borrowDAO = new BorrowDAO();
        bookDAO = new BookDAO();
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if ("manageBorrows".equals(action) && "admin".equals(role)) {
            List<BorrowDetail> borrowDetails = borrowDAO.getAllBorrowDetails();
            List<Customer> customers = customerDAO.getAllCustomers();
            List<Books> books = bookDAO.getAllBooks();
            request.setAttribute("borrowDetails", borrowDetails);
            request.setAttribute("customers", customers);
            request.setAttribute("books", books);
            request.getRequestDispatcher("/borrow/manageBorrows.jsp").forward(request, response);
        } else if ("editBorrow".equals(action) && "admin".equals(role)) {
            int id = Integer.parseInt(request.getParameter("id"));
            BorrowDetail borrowDetail = borrowDAO.getBorrowDetailById(id);
            List<Customer> customers = customerDAO.getAllCustomers();
            List<Books> books = bookDAO.getAllBooks();
            request.setAttribute("borrowDetail", borrowDetail);
            request.setAttribute("customers", customers);
            request.setAttribute("books", books);
            request.getRequestDispatcher("/borrow/editBorrow.jsp").forward(request, response);
        } else if ("borrowBooks".equals(action) && "user".equals(role)) {
            List<Customer> customers = customerDAO.getAllCustomers();
            List<Books> books = bookDAO.getAllBooks();
            request.setAttribute("customers", customers);
            request.setAttribute("books", books);
            request.getRequestDispatcher("/borrow/borrowBooks.jsp").forward(request, response);
        } else if ("deleteBorrow".equals(action) && "admin".equals(role)) {
            int id = Integer.parseInt(request.getParameter("id"));
            BorrowDetail borrowDetail = borrowDAO.getBorrowDetailById(id);
            request.setAttribute("borrowDetail", borrowDetail);
            request.getRequestDispatcher("/borrow/deleteBorrows.jsp").forward(request, response);
        }else {
            response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");

        if ("updateBorrow".equals(action) && "admin".equals(role)) {
            int id = Integer.parseInt(request.getParameter("id"));
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            Date borrowDate = Date.valueOf(request.getParameter("borrowDate"));
            Date returnDate = Date.valueOf(request.getParameter("returnDate"));
            String returnStatus = request.getParameter("returnStatus");
            Customer customer = customerDAO.getCustomerById(customerId);
            Books book = bookDAO.getBookById(bookId);
            BorrowDetail borrowDetail = new BorrowDetail(id, customer, book, borrowDate, returnDate, returnStatus);
            borrowDAO.updateBorrowDetail(borrowDetail);
            request.setAttribute("message", "Borrow updated successfully!");
            response.sendRedirect(request.getContextPath() + "/borrow?action=manageBorrows");
        } else if ("borrowBooks".equals(action) && "user".equals(role)) {
            int customerId = Integer.parseInt(request.getParameter("customerId"));
            int bookId = Integer.parseInt(request.getParameter("bookId"));
            Date borrowDate = Date.valueOf(request.getParameter("borrowDate"));
            Date returnDate = Date.valueOf(request.getParameter("returnDate"));
            if (borrowDAO.borrowBook(customerId, bookId, borrowDate, returnDate)) {
                request.setAttribute("message", "Book borrowed successfully!");
            } else {
                request.setAttribute("message", "Failed to borrow book.");
            }
            List<Customer> customers = customerDAO.getAllCustomers();
            List<Books> books = bookDAO.getAllBooks();
            request.setAttribute("customers", customers);
            request.setAttribute("books", books);
            request.getRequestDispatcher("/borrow/borrowBooks.jsp").forward(request, response);
        }else if ("confirmDeleteBorrow".equals(action) && "admin".equals(role)) {
            int id = Integer.parseInt(request.getParameter("id"));
            if (borrowDAO.deleteBorrowDetail(id)) {
                request.setAttribute("message", "Borrow detail deleted successfully!");
            } else {
                request.setAttribute("message", "Failed to delete borrow detail.");
            }
            response.sendRedirect(request.getContextPath() + "/borrow?action=manageBorrows");
        } else {
            response.sendRedirect(request.getContextPath() + "/dashboard.jsp");
        }

    }
}