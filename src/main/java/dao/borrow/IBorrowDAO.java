package dao.borrow;

import model.Borrow;
import model.BorrowDetail;

import java.sql.Date;
import java.util.List;

public interface IBorrowDAO {
    List<Borrow> getAllBorrows();
    List<Borrow> getBorrowsByCustomer(int customerId);
    boolean addBorrow(Borrow borrow);
    List<BorrowDetail> getBorrowDetails(int borrowId);
    boolean deleteBorrow(int id);
    boolean updateBorrow(Borrow borrow);
    Borrow getBorrowById(int id);
    boolean borrowBook(int customerId, int bookId, Date borrowDate, Date returnDate);
    List<BorrowDetail> getAllBorrowDetails();
    BorrowDetail getBorrowDetailById(int id);
    boolean updateBorrowDetail(BorrowDetail borrowDetail);

    boolean deleteBorrowDetail(int id);
}
