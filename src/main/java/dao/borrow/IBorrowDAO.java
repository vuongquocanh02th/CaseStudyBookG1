package dao.borrow;

import model.Borrow;
import java.util.List;

public interface IBorrowDAO {
    List<Borrow> getAllBorrows();
    Borrow getBorrowById(int id);
    void addBorrow(Borrow borrow);
    void updateBorrow(Borrow borrow);
    void deleteBorrow(int id);
}