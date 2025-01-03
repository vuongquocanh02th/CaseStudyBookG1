package service.borrow;

import dao.borrow.BorrowDAO;
import model.Borrow;

import java.util.Date;
import java.util.List;

public class BorrowService {
    private BorrowDAO borrowDAO;

    public BorrowService(BorrowDAO borrowDAO) {
        this.borrowDAO = borrowDAO;
    }

    public List<Borrow> getAllBorrows() {
        return borrowDAO.getAllBorrows();
    }

    public boolean borrowBook(int customerId, int bookId, Date borrowDate, Date returnDate) {
        return borrowDAO.borrowBook(customerId, bookId, (java.sql.Date) borrowDate, (java.sql.Date) returnDate);
    }
}