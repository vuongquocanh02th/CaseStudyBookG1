package controller;

import model.Borrow;
import model.BorrowDetail;
import model.Customer;
import model.Book;
import service.borrow.IBorrowService;
import service.borrow.BorrowServiceImpl;
import service.customer.ICustomerService;
import service.customer.CustomerServiceImpl;
import service.book.IBookService;
import service.book.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/borrows")
public class BorrowController extends HttpServlet {
    private IBorrowService borrowService = new BorrowServiceImpl();
    private ICustomerService customerService = new CustomerServiceImpl();
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
            case "details":
                showBorrowDetails(request, response);
                break;
            default:
                listBorrows(request, response);
                break;
        }
    }

    private void listBorrows(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Borrow> borrows = borrowService.findAll();
        request.setAttribute("borrows", borrows);
        request.getRequestDispatcher("/borrows/list.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customers = customerService.findAll();
        List<Book> books = bookService.findAll();
        request.setAttribute("customers", customers);
        request.setAttribute("books", books);
        request.getRequestDispatcher("/borrows/add.jsp").forward(request, response);
    }

    private void showBorrowDetails(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        List<BorrowDetail> borrowDetails = borrowService.findDetailsByBorrowId(id);
        request.setAttribute("borrowDetails", borrowDetails);
        request.getRequestDispatcher("/borrows/details.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "add":
                addBorrow(request, response);
                break;
            case "delete":
                deleteBorrow(request, response);
                break;
        }
    }

    private void addBorrow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        String borrowDate = request.getParameter("borrowDate");
        String returnDate = request.getParameter("returnDate");

        Borrow newBorrow = new Borrow(customerId, borrowDate, returnDate);
        borrowService.save(newBorrow);

        BorrowDetail borrowDetail = new BorrowDetail(newBorrow.getId(), bookId, borrowDate, returnDate, "Pending");
        borrowService.saveBorrowDetail(borrowDetail);

        response.sendRedirect("/borrows");
    }

    private void deleteBorrow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        borrowService.delete(id);
        response.sendRedirect("/borrows");
    }
}