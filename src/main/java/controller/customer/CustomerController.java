package controller.customer;

import dao.customer.CustomerDAO;
import model.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;

@WebServlet("/customers")
public class CustomerController extends HttpServlet {
    private CustomerDAO customerDAO;

    public void init() {
        customerDAO = new CustomerDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("edit".equals(action)) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                Customer existingCustomer = customerDAO.getCustomerById(id);
                if (existingCustomer != null) {
                    req.setAttribute("customer", existingCustomer);
                    req.getRequestDispatcher("customer/editCustomer.jsp").forward(req, resp);
                } else {
                    req.setAttribute("errorMessage", "Customer not found.");
                    req.getRequestDispatcher("error.jsp").forward(req, resp);
                }
            } catch (NumberFormatException e) {
                req.setAttribute("errorMessage", "Invalid customer ID format.");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        } else if ("delete".equals(action)) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                Customer customer = customerDAO.getCustomerById(id);
                if (customer != null) {
                    req.setAttribute("customer", customer);
                    req.getRequestDispatcher("customer/deleteCustomer.jsp").forward(req, resp);
                } else {
                    req.setAttribute("errorMessage", "Customer not found.");
                    req.getRequestDispatcher("error.jsp").forward(req, resp);
                }
            } catch (NumberFormatException e) {
                req.setAttribute("errorMessage", "Invalid customer ID format.");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("customers", customerDAO.getAllCustomers());
            req.getRequestDispatcher("customer/customer.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("edit".equals(action)) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                String name = req.getParameter("name");
                String schoolName = req.getParameter("schoolName");
                String address = req.getParameter("address");
                Date dob = Date.valueOf(req.getParameter("dob"));

                Customer customer = new Customer(id, name, schoolName, address, dob);
                boolean success = customerDAO.updateCustomer(customer);

                // Set success or failure message
                if (success) {
                    req.setAttribute("message", "Customer successfully updated!");
                } else {
                    req.setAttribute("message", "Customer update failed! Try Again!");
                }

                // Forward back to the edit page with the success message
                req.setAttribute("customer", customer); // Send the updated customer back to the page
                req.getRequestDispatcher("customer/editCustomer.jsp").forward(req, resp);
            } catch (Exception e) {
                req.setAttribute("errorMessage", "An error occurred while processing the request.");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        } else if ("delete".equals(action)) {
            try {
                int id = Integer.parseInt(req.getParameter("id"));
                boolean success = customerDAO.deleteCustomer(id);

                // Thiết lập thông báo sau khi xóa
                if (success) {
                    req.setAttribute("message", "Customer successfully deleted.");
                } else {
                    req.setAttribute("message", "Customer deletion failed. Try again.");
                }

                // Chuyển hướng lại trang danh sách khách hàng
                req.setAttribute("customers", customerDAO.getAllCustomers());
                req.getRequestDispatcher("customer/customer.jsp").forward(req, resp);  // Redirect to customer list
            } catch (NumberFormatException e) {
                req.setAttribute("errorMessage", "Invalid customer ID format.");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            } catch (Exception e) {
                req.setAttribute("errorMessage", "An error occurred while deleting the customer.");
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        }
    }
}
