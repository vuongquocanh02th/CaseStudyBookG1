package dao;

import model.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CustomerDAO implements ICustomerDAO{
    private String jdbcURL = "jdbc:mysql://localhost:3306/bookdb?useSSL=false";
    private String jdbcUsername = "root";
    private String jdbcPassword = "123456";

    private static final String SELECT_ALL_CUSTOMERS = "SELECT * FROM Customer";
    private static final String SELECT_CUSTOMER_BY_ID = "select * from customer where id = ?";
    private static final String UPDATE_CUSTOMER = "UPDATE Customer SET Name = ?, SchoolName = ?, Address = ?, DOB = ? WHERE ID = ?";
    private static final String DELETE_CUSTOMER = "DELETE FROM Customer WHERE ID = ?";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        try(Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(SELECT_ALL_CUSTOMERS);){
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                Customer customer = new Customer(
                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("SchoolName"),
                        resultSet.getString("Address"),
                        resultSet.getDate("DOB")
                );
                customers.add(customer);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return customers;
    }

    @Override
    public Customer getCustomerById(int id) {
        Customer customer = null;
        try(Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_CUSTOMER_BY_ID);){
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()) {
                customer = new Customer(
                        resultSet.getInt("ID"),
                        resultSet.getString("Name"),
                        resultSet.getString("SchoolName"),
                        resultSet.getString("Address"),
                        resultSet.getDate("DOB")
                );
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return customer;
    }

    @Override
    public boolean updateCustomer(Customer customer) {
        try(Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_CUSTOMER)) {
            statement.setString(1, customer.getName());
            statement.setString(2, customer.getSchoolName());
            statement.setString(3, customer.getAddress());
            statement.setDate(4, new java.sql.Date(customer.getDob().getTime()));
            statement.setInt(5, customer.getId());
            return statement.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteCustomer(int id) {
        try(Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_CUSTOMER);) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
