package dao.customer;

import model.Customer;
import java.util.List;

public interface ICustomerDAO {
    List<Customer> getAllCustomers();
    Customer getCustomerById(int id);
    void addCustomer(Customer customer);
    void updateCustomer(Customer customer);
    void deleteCustomer(int id);
}
