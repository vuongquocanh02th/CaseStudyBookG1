package model;

import java.util.Date;

public class BorrowDetail {
    private int id;
    private int borrowId;
    private int bookId;
    private Date borrowDate;
    private Date returnDate;
    private String returnStatus; // Pending, Returned, Overdue

    // Constructors
    public BorrowDetail() {}

    public BorrowDetail(int id, int borrowId, int bookId, Date borrowDate, Date returnDate, String returnStatus) {
        this.id = id;
        this.borrowId = borrowId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.returnStatus = returnStatus;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(int borrowId) {
        this.borrowId = borrowId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }
}

