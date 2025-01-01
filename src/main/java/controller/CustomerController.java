package controller;

import dao.CustomerDAO;
import model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;


@WebServlet("/customers")
public class CustomerController extends HttpServlet {
    private CustomerDAO customerDAO;

    public void init() {
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if("edit".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            Customer existingCustomer = customerDAO.getCustomerById(id);
            req.setAttribute("customer", existingCustomer);
            req.getRequestDispatcher("customer/editCustomer.jsp").forward(req, resp);
        }else {
            List<Customer> customers = customerDAO.getAllCustomers();
            req.setAttribute("customers", customers);
            req.getRequestDispatcher("customer/customer.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if("edit".equals(action)) {
            int id = Integer.parseInt(req.getParameter("id"));
            String name = req.getParameter("name");
            String schoolName = req.getParameter("schoolName");
            String address = req.getParameter("address");
            Date dob = Date.valueOf(req.getParameter("dob"));

            Customer customer = new Customer(id, name, schoolName, address, dob);
            customerDAO.updateCustomer(customer);
            resp.sendRedirect("/customers");
        }
    }
}
