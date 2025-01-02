package dao.borrow;

import model.Borrow;
import utils.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowDAOImpl implements IBorrowDAO {

    @Override
    public List<Borrow> getAllBorrows() {
        List<Borrow> borrows = new ArrayList<>();
        String query = "SELECT * FROM borrows";
        try (Connection connection = DBConnection.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                Borrow borrow = new Borrow();
                borrow.setId(resultSet.getInt("id"));
                borrow.setCustomerId(resultSet.getInt("customer_id"));
                borrow.setBookId(resultSet.getInt("book_id"));
                borrow.setBorrowDate(resultSet.getString("borrow_date"));
                borrows.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrows;
    }

    @Override
    public Borrow getBorrowById(int id) {
        Borrow borrow = null;
        String query = "SELECT * FROM borrows WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                borrow = new Borrow();
                borrow.setId(resultSet.getInt("id"));
                borrow.setCustomerId(resultSet.getInt("customer_id"));
                borrow.setBookId(resultSet.getInt("book_id"));
                borrow.setBorrowDate(resultSet.getString("borrow_date"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrow;
    }

    @Override
    public void addBorrow(Borrow borrow) {
        String query = "INSERT INTO borrows (customer_id, book_id, borrow_date) VALUES (?, ?, ?)";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, borrow.getCustomerId());
            preparedStatement.setInt(2, borrow.getBookId());
            preparedStatement.setString(3, borrow.getBorrowDate());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateBorrow(Borrow borrow) {
        String query = "UPDATE borrows SET customer_id = ?, book_id = ?, borrow_date = ? WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, borrow.getCustomerId());
            preparedStatement.setInt(2, borrow.getBookId());
            preparedStatement.setString(3, borrow.getBorrowDate());
            preparedStatement.setInt(4, borrow.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteBorrow(int id) {
        String query = "DELETE FROM borrows WHERE id = ?";
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}