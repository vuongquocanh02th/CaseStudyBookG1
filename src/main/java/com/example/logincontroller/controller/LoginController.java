package com.example.logincontroller.controller;
import java.io.IOException;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/login/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Simple login check with roles
        HttpSession session = request.getSession();

        if ("admin".equals(username) && "123".equals(password)) {
            session.setAttribute("role", "admin");
            session.setAttribute("user", username);
            response.sendRedirect("/categories?action=list"); // Admin: Manage categories
        } else if ("librarian".equals(username) && "123".equals(password)) {
            session.setAttribute("role", "librarian");
            session.setAttribute("user", username);
            response.sendRedirect("/customers?action=list"); // Librarian: Manage customers
        } else if ("customer".equals(username) && "123".equals(password)) {
            session.setAttribute("role", "customer");
            session.setAttribute("user", username);
            response.sendRedirect("/books?action=list"); // Customer: Search books
        } else {
            request.setAttribute("error", "Invalid username or password");
            request.getRequestDispatcher("/login/login.jsp").forward(request, response);
        }
    }
}