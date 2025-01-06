package service.borrow;

import dao.borrow.IBorrowDAO;
import dao.borrow.BorrowDAOImpl;
import model.Borrow;
import model.BorrowDetail;

import java.util.List;

public class BorrowServiceImpl implements IBorrowService {
    private IBorrowDAO borrowDAO = new BorrowDAOImpl();

    @Override
    public List<Borrow> findAll() {
        return borrowDAO.findAll();
    }

    @Override
    public Borrow findById(int id) {
        return borrowDAO.findById(id);
    }

    @Override
    public void save(Borrow borrow) {
        borrowDAO.save(borrow);
    }

    @Override
    public void update(Borrow borrow) {
        borrowDAO.update(borrow);
    }

    @Override
    public void delete(int id) {
        borrowDAO.delete(id);
    }

    @Override
    public List<BorrowDetail> findDetailsByBorrowId(int borrowId) {
        return borrowDAO.findDetailsByBorrowId(borrowId);
    }

    @Override
    public List<BorrowDetail> findDetailsByCustomerId(int customerId) {
        return borrowDAO.findDetailsByCustomerId(customerId);
    }

    @Override
    public void saveBorrowDetail(BorrowDetail borrowDetail) {
        borrowDAO.saveBorrowDetail(borrowDetail);
    }
}