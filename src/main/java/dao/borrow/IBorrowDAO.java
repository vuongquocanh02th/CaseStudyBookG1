package dao.borrow;

import model.Borrow;
import model.BorrowDetail;

import java.util.List;

public interface IBorrowDAO {
    List<Borrow> getAllBorrows();
    List<Borrow> getBorrowsByCustomer(int customerId);
    boolean addBorrow(Borrow borrow);
    List<BorrowDetail> getBorrowDetails(int borrowId);
}
