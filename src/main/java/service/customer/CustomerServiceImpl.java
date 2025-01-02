package service.customer;

import dao.customer.ICustomerDAO;
import dao.customer.CustomerDAOImpl;
import model.Customer;
import java.util.List;

public class CustomerServiceImpl implements ICustomerService {
    private ICustomerDAO customerDAO = new CustomerDAOImpl();

    @Override
    public List<Customer> getAllCustomers() {
        return customerDAO.getAllCustomers();
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerDAO.getCustomerById(id);
    }

    @Override
    public void addCustomer(Customer customer) {
        customerDAO.addCustomer(customer);
    }

    @Override
    public void updateCustomer(Customer customer) {
        customerDAO.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(int id) {
        customerDAO.deleteCustomer(id);
    }
}