package controller;

import model.Borrow;
import service.borrow.IBorrowService;
import service.borrow.BorrowServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class BorrowController extends HttpServlet {
    private IBorrowService borrowService;

    @Override
    public void init() throws ServletException {
        borrowService = new BorrowServiceImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "new":
                showNewForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteBorrow(request, response);
                break;
            default:
                listBorrows(request, response);
                break;
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "insert":
                insertBorrow(request, response);
                break;
            case "update":
                updateBorrow(request, response);
                break;
            default:
                listBorrows(request, response);
                break;
        }
    }

    private void listBorrows(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Borrow> listBorrow = borrowService.getAllBorrows();
        request.setAttribute("listBorrow", listBorrow);
        RequestDispatcher dispatcher = request.getRequestDispatcher("borrows/list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("borrows/add.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Borrow existingBorrow = borrowService.getBorrowById(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("borrows/edit.jsp");
        request.setAttribute("borrow", existingBorrow);
        dispatcher.forward(request, response);
    }

    private void insertBorrow(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int customerId = Integer.parseInt(request.getParameter("customer_id"));
        int bookId = Integer.parseInt(request.getParameter("book_id"));
        String borrowDate = request.getParameter("borrow_date");
        Borrow newBorrow = new Borrow();
        newBorrow.setCustomerId(customerId);
        newBorrow.setBookId(bookId);
        newBorrow.setBorrowDate(borrowDate);
        borrowService.addBorrow(newBorrow);
        response.sendRedirect("list");
    }

    private void updateBorrow(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int customerId = Integer.parseInt(request.getParameter("customer_id"));
        int bookId = Integer.parseInt(request.getParameter("book_id"));
        String borrowDate = request.getParameter("borrow_date");

        Borrow borrow = new Borrow();
        borrow.setId(id);
        borrow.setCustomerId(customerId);
        borrow.setBookId(bookId);
        borrow.setBorrowDate(borrowDate);

        borrowService.updateBorrow(borrow);
        response.sendRedirect("list");
    }

    private void deleteBorrow(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        borrowService.deleteBorrow(id);
        response.sendRedirect("list");
    }
}