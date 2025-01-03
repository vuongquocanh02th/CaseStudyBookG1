package dao.borrow;

import model.Borrow;
import model.BorrowDetail;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowDAOImpl implements IBorrowDAO {
    private static final String SELECT_ALL_BORROWS = "SELECT b.ID, b.CustomerID, c.Name AS CustomerName, b.BorrowDate, b.ReturnDate FROM Borrow b JOIN Customer c ON b.CustomerID = c.ID";
    private static final String SELECT_BORROW_BY_ID = "SELECT b.ID, b.CustomerID, c.Name AS CustomerName, b.BorrowDate, b.ReturnDate FROM Borrow b JOIN Customer c ON b.CustomerID = c.ID WHERE b.ID = ?";
    private static final String INSERT_BORROW = "INSERT INTO Borrow (CustomerID, BorrowDate, ReturnDate) VALUES (?, ?, ?)";
    private static final String UPDATE_BORROW = "UPDATE Borrow SET CustomerID = ?, BorrowDate = ?, ReturnDate = ? WHERE ID = ?";
    private static final String DELETE_BORROW = "DELETE FROM Borrow WHERE ID = ?";
    private static final String SELECT_BORROW_DETAILS_BY_BORROW_ID = "SELECT bd.ID, bd.BorrowID, bd.BookID, bk.BookName, bd.BorrowDate, bd.ReturnDate, bd.ReturnStatus FROM BorrowDetail bd JOIN Books bk ON bd.BookID = bk.ID WHERE bd.BorrowID = ?";
    private static final String SELECT_BORROW_DETAILS_BY_CUSTOMER_ID = "SELECT bd.ID, bd.BorrowID, bd.BookID, bk.BookName, bd.BorrowDate, bd.ReturnDate, bd.ReturnStatus " +
            "FROM BorrowDetail bd " +
            "JOIN Books bk ON bd.BookID = bk.ID " +
            "JOIN Borrow b ON bd.BorrowID = b.ID " +
            "WHERE b.CustomerID = ?";

    @Override
    public List<Borrow> findAll() {
        List<Borrow> borrows = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BORROWS)) {
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                int customerId = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                String borrowDate = rs.getString("BorrowDate");
                String returnDate = rs.getString("ReturnDate");
                borrows.add(new Borrow(id, customerId, customerName, borrowDate, returnDate));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrows;
    }

    @Override
    public Borrow findById(int id) {
        Borrow borrow = null;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BORROW_BY_ID)) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                int customerId = rs.getInt("CustomerID");
                String customerName = rs.getString("CustomerName");
                String borrowDate = rs.getString("BorrowDate");
                String returnDate = rs.getString("ReturnDate");
                borrow = new Borrow(id, customerId, customerName, borrowDate, returnDate);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrow;
    }

    @Override
    public void save(Borrow borrow) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BORROW, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setInt(1, borrow.getCustomerId());
            preparedStatement.setString(2, borrow.getBorrowDate());
            preparedStatement.setString(3, borrow.getReturnDate());
            preparedStatement.executeUpdate();

            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()) {
                borrow.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Borrow borrow) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BORROW)) {
            preparedStatement.setInt(1, borrow.getCustomerId());
            preparedStatement.setString(2, borrow.getBorrowDate());
            preparedStatement.setString(3, borrow.getReturnDate());
            preparedStatement.setInt(4, borrow.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int id) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BORROW)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<BorrowDetail> findDetailsByBorrowId(int borrowId) {
        List<BorrowDetail> borrowDetails = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BORROW_DETAILS_BY_BORROW_ID)) {
            preparedStatement.setInt(1, borrowId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                int bookId = rs.getInt("BookID");
                String bookName = rs.getString("BookName");
                String borrowDate = rs.getString("BorrowDate");
                String returnDate = rs.getString("ReturnDate");
                String returnStatus = rs.getString("ReturnStatus");
                borrowDetails.add(new BorrowDetail(id, borrowId, bookId, bookName, borrowDate, returnDate, returnStatus));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrowDetails;
    }

    @Override
    public List<BorrowDetail> findDetailsByCustomerId(int customerId) {
        List<BorrowDetail> borrowDetails = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BORROW_DETAILS_BY_CUSTOMER_ID)) {
            preparedStatement.setInt(1, customerId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("ID");
                int borrowId = rs.getInt("BorrowID");
                int bookId = rs.getInt("BookID");
                String bookName = rs.getString("BookName");
                String borrowDate = rs.getString("BorrowDate");
                String returnDate = rs.getString("ReturnDate");
                String returnStatus = rs.getString("ReturnStatus");
                borrowDetails.add(new BorrowDetail(id, borrowId, bookId, bookName, borrowDate, returnDate, returnStatus));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrowDetails;
    }

    @Override
    public void saveBorrowDetail(BorrowDetail borrowDetail) {
        String INSERT_BORROW_DETAIL = "INSERT INTO BorrowDetail (BorrowID, BookID, BorrowDate, ReturnDate, ReturnStatus) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BORROW_DETAIL)) {
            preparedStatement.setInt(1, borrowDetail.getBorrowId());
            preparedStatement.setInt(2, borrowDetail.getBookId());
            preparedStatement.setString(3, borrowDetail.getBorrowDate());
            preparedStatement.setString(4, borrowDetail.getReturnDate());
            preparedStatement.setString(5, borrowDetail.getReturnStatus());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}