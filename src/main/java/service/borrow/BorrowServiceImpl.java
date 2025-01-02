package service.borrow;

import dao.borrow.IBorrowDAO;
import dao.borrow.BorrowDAOImpl;
import model.Borrow;
import java.util.List;

public class BorrowServiceImpl implements IBorrowService {
    private IBorrowDAO borrowDAO = new BorrowDAOImpl();

    @Override
    public List<Borrow> getAllBorrows() {
        return borrowDAO.getAllBorrows();
    }

    @Override
    public Borrow getBorrowById(int id) {
        return borrowDAO.getBorrowById(id);
    }

    @Override
    public void addBorrow(Borrow borrow) {
        borrowDAO.addBorrow(borrow);
    }

    @Override
    public void updateBorrow(Borrow borrow) {
        borrowDAO.updateBorrow(borrow);
    }

    @Override
    public void deleteBorrow(int id) {
        borrowDAO.deleteBorrow(id);
    }
}