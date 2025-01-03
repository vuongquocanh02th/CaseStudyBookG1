package dao.borrow;

import dbconnect.DBConnection;
import model.Borrow;
import model.BorrowDetail;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BorrowDAO implements IBorrowDAO {
    private static final String SELECT_ALL_BORROW = "SELECT * FROM Borrow";
    private static final String SELECT_BORROW_BY_CUSTOMER = "SELECT * FROM Borrow WHERE CustomerID = ?";
    private static final String INSERT_BORROW = "INSERT INTO Borrow (CustomerID, BorrowDate, ReturnDate) VALUES (?, ?, ?)";
    private static final String SELECT_BORROW_DETAIL = "SELECT * FROM BorrowDetail WHERE BorrowID = ?";
    private static final String INSERT_BORROW_DETAIL = "INSERT INTO BorrowDetail (BorrowID, BookID, BorrowDate, ReturnDate, ReturnStatus) VALUES (?, ?, ?, ?, ?)";

    @Override
    public List<Borrow> getAllBorrows() {
        List<Borrow> borrows = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL_BORROW);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Borrow borrow = new Borrow(
                        rs.getInt("ID"),
                        rs.getInt("CustomerID"),
                        rs.getDate("BorrowDate"),
                        rs.getDate("ReturnDate")
                );
                borrows.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrows;
    }

    @Override
    public List<Borrow> getBorrowsByCustomer(int customerId) {
        List<Borrow> borrows = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_BORROW_BY_CUSTOMER)) {
            ps.setInt(1, customerId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Borrow borrow = new Borrow(
                        rs.getInt("ID"),
                        rs.getInt("CustomerID"),
                        rs.getDate("BorrowDate"),
                        rs.getDate("ReturnDate")
                );
                borrows.add(borrow);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrows;
    }

    @Override
    public boolean addBorrow(Borrow borrow) {
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_BORROW)) {
            ps.setInt(1, borrow.getCustomerId());
            ps.setDate(2, new java.sql.Date(borrow.getBorrowDate().getTime()));
            ps.setDate(3, new java.sql.Date(borrow.getReturnDate().getTime()));
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<BorrowDetail> getBorrowDetails(int borrowId) {
        List<BorrowDetail> borrowDetails = new ArrayList<>();
        String query = "SELECT * FROM BorrowDetail WHERE BorrowID = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, borrowId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BorrowDetail borrowDetail = new BorrowDetail(
                        rs.getInt("ID"),
                        rs.getInt("BorrowID"),
                        rs.getInt("BookID"),
                        rs.getDate("BorrowDate"),
                        rs.getDate("ReturnDate"),
                        rs.getString("ReturnStatus")
                );
                borrowDetails.add(borrowDetail);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrowDetails;
    }

    public boolean borrowBook(int customerId, int bookId, Date borrowDate, Date returnDate) {
        Connection conn = null;
        PreparedStatement psBorrow = null;
        PreparedStatement psBorrowDetail = null;
        ResultSet rs = null;

        try {
            conn = DBConnection.getConnection();
            conn.setAutoCommit(false); // Bắt đầu giao dịch

            // Thêm bản ghi vào bảng Borrow
            psBorrow = conn.prepareStatement(INSERT_BORROW, Statement.RETURN_GENERATED_KEYS);
            psBorrow.setInt(1, customerId);
            psBorrow.setDate(2, new java.sql.Date(borrowDate.getTime()));
            psBorrow.setDate(3, new java.sql.Date(returnDate.getTime()));
            psBorrow.executeUpdate();

            // Lấy ID của bản ghi Borrow vừa được thêm
            rs = psBorrow.getGeneratedKeys();
            int borrowId = 0;
            if (rs.next()) {
                borrowId = rs.getInt(1);
            }

            // Thêm bản ghi vào bảng BorrowDetail
            psBorrowDetail = conn.prepareStatement(INSERT_BORROW_DETAIL);
            psBorrowDetail.setInt(1, borrowId);
            psBorrowDetail.setInt(2, bookId);
            psBorrowDetail.setDate(3, new java.sql.Date(borrowDate.getTime()));
            psBorrowDetail.setDate(4, new java.sql.Date(returnDate.getTime()));
            psBorrowDetail.setString(5, "Pending");
            psBorrowDetail.executeUpdate();

            conn.commit(); // Hoàn tất giao dịch
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback(); // Rollback nếu có lỗi
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            try {
                if (rs != null) rs.close();
                if (psBorrow != null) psBorrow.close();
                if (psBorrowDetail != null) psBorrowDetail.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}