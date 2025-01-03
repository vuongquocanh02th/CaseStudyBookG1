package dao.customer;

import dbconnect.DBConnection;
import model.Customer;
import dbconnect.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements ICustomerDAO {
    private dbconnect.DBConnection DBConnection;
    private Connection connection = DBConnection.getConnection();

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String query = "SELECT * FROM Customer";

        try (PreparedStatement ps = connection.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Customer customer = new Customer(
                        rs.getInt("ID"),
                        rs.getString("Name"),
                        rs.getString("SchoolName"),
                        rs.getString("Address"),
                        rs.getString("DOB")
                );
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer getCustomerById(int id) {
        Customer customer = null;
        String query = "SELECT * FROM Customer WHERE ID = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    customer = new Customer(
                            rs.getInt("ID"),
                            rs.getString("Name"),
                            rs.getString("SchoolName"),
                            rs.getString("Address"),
                            rs.getString("DOB")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public void addCustomer(Customer customer) {
        String query = "INSERT INTO Customer (Name, SchoolName, Address, DOB) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getSchoolName());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getDob());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateCustomer(Customer customer) {
        String query = "UPDATE Customer SET Name = ?, SchoolName = ?, Address = ?, DOB = ? WHERE ID = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, customer.getName());
            ps.setString(2, customer.getSchoolName());
            ps.setString(3, customer.getAddress());
            ps.setString(4, customer.getDob());
            ps.setInt(5, customer.getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteCustomer(int id) {
        String query = "DELETE FROM Customer WHERE ID = ?";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
