package controller;

import model.Borrow;
import model.BorrowDetail;
import service.borrow.IBorrowService;
import service.borrow.BorrowServiceImpl;

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
        String borrowDate = request.getParameter("borrowDate");
        String returnDate = request.getParameter("returnDate");

        Borrow newBorrow = new Borrow(customerId, borrowDate, returnDate);
        borrowService.save(newBorrow);
        response.sendRedirect("/borrows");
    }

    private void deleteBorrow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        borrowService.delete(id);
        response.sendRedirect("/borrows");
    }
}