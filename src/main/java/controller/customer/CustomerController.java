package controller.customer;

import dao.customer.CustomerDAO;
import model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CustomerController", urlPatterns = {"/customers"})
public class CustomerController extends HttpServlet {
    private CustomerDAO customerDAO = new CustomerDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "add":
                showAddForm(request, response);
                break;
            case "edit":
                showEditForm(request, response);
                break;
            case "delete":
                deleteCustomer(request, response);
                break;
            default:
                listCustomers(request, response);
                break;
        }
    }

    private void listCustomers(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Customer> customers = customerDAO.getAllCustomers();
        request.setAttribute("customers", customers);
        request.getRequestDispatcher("/customers/list.jsp").forward(request, response);
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/customers/add.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerDAO.getCustomerById(id);
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("/customers/edit.jsp").forward(request, response);
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        customerDAO.deleteCustomer(id);
        response.sendRedirect("/customers");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "add";
        }

        switch (action) {
            case "add":
                addCustomer(request, response);
                break;
            case "edit":
                updateCustomer(request, response);
                break;
        }
    }

    private void addCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String schoolName = request.getParameter("schoolName");
        String address = request.getParameter("address");
        String dob = request.getParameter("dob");

        Customer customer = new Customer(name, schoolName, address, dob);
        customerDAO.addCustomer(customer);
        response.sendRedirect("/customers");
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String schoolName = request.getParameter("schoolName");
        String address = request.getParameter("address");
        String dob = request.getParameter("dob");

        Customer customer = new Customer(id, name, schoolName, address, dob);
        customerDAO.updateCustomer(customer);
        response.sendRedirect("/customers");
    }
}
