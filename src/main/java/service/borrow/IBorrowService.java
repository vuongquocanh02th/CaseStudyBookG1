package service.borrow;

import model.Borrow;
import model.BorrowDetail;
import java.util.List;

public interface IBorrowService {
    List<Borrow> findAll();
    Borrow findById(int id);
    void save(Borrow borrow);
    void update(Borrow borrow);
    void delete(int id);
    List<BorrowDetail> findDetailsByBorrowId(int borrowId);
}