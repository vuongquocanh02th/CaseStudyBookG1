package dao.customer;

import model.Customer;
import java.util.List;

public interface ICustomerDAO {
    List<Customer> findAll();
    Customer findById(int id);
    void save(Customer customer);
    void update(Customer customer);
    void delete(int id);
}