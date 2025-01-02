package service.borrow;

import model.Borrow;
import java.util.List;

public interface IBorrowService {
    List<Borrow> getAllBorrows();
    Borrow getBorrowById(int id);
    void addBorrow(Borrow borrow);
    void updateBorrow(Borrow borrow);
    void deleteBorrow(int id);
}