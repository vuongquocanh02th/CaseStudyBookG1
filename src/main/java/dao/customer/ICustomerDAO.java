package dao.customer;

import model.Customer;

import java.util.List;

public interface ICustomerDAO {
    List<Customer> getAllCustomers();
    Customer getCustomerById(int id);
    boolean updateCustomer(Customer customer);
    boolean deleteCustomer(int id);
}