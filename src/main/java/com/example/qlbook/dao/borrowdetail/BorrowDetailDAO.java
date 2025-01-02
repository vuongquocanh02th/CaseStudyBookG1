package com.example.qlbook.dao.borrowdetail;

import com.example.qlbook.model.BorrowDetail;
import com.example.qlbook.utils.DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BorrowDetailDAO implements IBorrowDetailDAO {
    private static final String INSERT_BORROWDETAIL = "insert into borrowdetail (id,borrow_id,book_id) values (?,?,?)  ";
    private static final String SELECT_BORROWDETAIL_BY_ID = "select * from borrowdetail where id=?";
    private static final String SELECT_ALL = "select * from borrowdetail";
    private static final String UPDATE_BORROWDETAIL = "update borrowdetail set book_id=?,borrow_id=? where id=?";
    private static final String DELETE_BORROWDETAIL = "delete from borrowdetail where id=?";

    public BorrowDetailDAO() {
    }

    public void insertBorrowDetail(BorrowDetail borrowDetail) throws SQLException {
        System.out.println(INSERT_BORROWDETAIL);
        try(Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(INSERT_BORROWDETAIL)) {
            statement.setInt(1, borrowDetail.getId());
            statement.setInt(2,borrowDetail.getBorrow_id());
            statement.setInt(3,borrowDetail.getBook_id());
            System.out.println(statement);
            statement.executeUpdate()
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public boolean updateBorrowDetail(BorrowDetail borrowDetail) throws SQLException {
        boolean rowUpdated = false;
        try(Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(UPDATE_BORROWDETAIL)) {
            statement.setInt(1, borrowDetail.getId());
            statement.setInt(2,borrowDetail.getBorrow_id());
            statement.setInt(3,borrowDetail.getBook_id());
            statement.executeUpdate();
            rowUpdated = statement.executeUpdate() > 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return rowUpdated;
    }

    public boolean deleteBorrowDetail(int id) throws SQLException {
        boolean rowDeleted = false;
        try(Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(DELETE_BORROWDETAIL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public BorrowDetail selectBorrowDetailById(int id) throws SQLException {
        BorrowDetail borrowDetail = null;
        try(Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_BORROWDETAIL_BY_ID)) {
            statement.setInt(1, id);
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int borrowdetail_id = resultSet.getInt("id");
                int borrow_id = resultSet.getInt("borrow_id");
                int book_id = resultSet.getInt("book_id");
                BorrowDetail bd = new BorrowDetail(borrowdetail_id,borrow_id,book_id);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return borrowDetail;
    }

    public List<BorrowDetail> selectAllBorrowDetail() throws SQLException {
        List<BorrowDetail> borrowDetails = null;
        try(Connection connection = DataBaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement(SELECT_ALL)) {
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int borrow_id = resultSet.getInt("borrow_id");
                int book_id = resultSet.getInt("book_id");
                BorrowDetail bd = new BorrowDetail(id,borrow_id,book_id);
            }
        }
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
