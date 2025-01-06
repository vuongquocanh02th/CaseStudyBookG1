package dao.customer;

import model.Customer;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements ICustomerDAO {
    private static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM Customer";
    private static final String SELECT_CUSTOMER_BY_ID = "SELECT * FROM Customer WHERE ID = ?";
    private static final String INSERT_CUSTOMER = "INSERT INTO Customer (Name, SchoolName, Address, DOB) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_CUSTOMER = "UPDATE Customer SET Name = ?, SchoolName = ?, Address = ?, DOB = ? WHERE ID = ?";
    private static final String DELETE_CUSTOMER = "DELETE FROM Customer WHERE ID = ?";

    @Override
    public List<Customer> findAll() {
        List<Customer> customers = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CUSTOMERS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                String name = rs.getString("Name");
                String schoolName = rs.getString("SchoolName");
                String address = rs.getString("Address");
                String dob = rs.getString("DOB");
                customers.add(new Customer(id, name, schoolName, address, dob));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer findById(int id) {
        Customer customer = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                String name = rs.getString("Name");
                String schoolName = rs.getString("SchoolName");
                String address = rs.getString("Address");
                String dob = rs.getString("DOB");
                customer = new Customer(id, name, schoolName, address, dob);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public void save(Customer customer) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER)) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getSchoolName());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getDob());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Customer customer) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER)) {
            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getSchoolName());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setString(4, customer.getDob());
            preparedStatement.setInt(5, customer.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CUSTOMER)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}