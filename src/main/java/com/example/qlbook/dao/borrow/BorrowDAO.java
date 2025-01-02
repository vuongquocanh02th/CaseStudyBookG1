package com.example.qlbook.dao.borrow;

import com.example.qlbook.model.Borrow;
import com.example.qlbook.utils.DataBaseConnection;

import java.sql.*;
import java.util.List;

public class BorrowDAO implements IBorrowDAO{
    private static final String INSERT_BORROW = "insert into borrow (id,student_id,dateTime) values (?,?,?)";
    private static final String SELECT_BY_ID = "select * from borrow where id=?";
    private static final String SELECT_ALL = "select * from borrow";
    private static final String DELETE_BY_ID = "delete from borrow where id=?";
    private static final String UPDATE_BORROW = "update borrow set student_id=?,dateTime=? where id=?";

    public BorrowDAO() {

    }

    public void insertBorrow(Borrow borrow) throws SQLException {
        System.out.println(INSERT_BORROW);
        try (Connection connection = DataBaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERT_BORROW)) {
            statement.setInt(1, borrow.getId());
            statement.setInt(2, borrow.getStudent_id());
            statement.setTime(3, borrow.getDateTime());
            System.out.println(statement);
            statement.executeUpdate();
        }catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean updateBorrow(Borrow borrow) throws SQLException {
        boolean rowUpdated = false;
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_BORROW)) {
            statement.setInt(1, borrow.getId());
            statement.setInt(2, borrow.getStudent_id());
            statement.setTime(3, borrow.getDateTime());
            System.out.println(statement);
            rowUpdated = statement.executeUpdate() > 0;
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return rowUpdated;
    }

    public boolean deleteBorrow(int id) throws SQLException {
        boolean rowDeleted = false;
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_BY_ID)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public Borrow selectBorrowById(int id) throws SQLException {
        Borrow borrow = null;
        try (Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int borrowId = resultSet.getInt("id");
                int student_id = resultSet.getInt("student_id");
                Time dateTime = resultSet.getTime("dateTime");
                borrow = new Borrow(borrowId, student_id, dateTime);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return borrow;
    }

    public List<Borrow> selectAllBorrow() throws SQLException {
        List<Borrow> borrows = null;
    try (Connection connection = DataBaseConnection.getConnection();
    PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
        System.out.println(statement);
        ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int student_id = resultSet.getInt("student_id");
                Time dateTime = resultSet.getTime("dateTime");
                Borrow borrow = new Borrow(id, student_id, dateTime);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return borrows;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + ((SQLException) e).getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
