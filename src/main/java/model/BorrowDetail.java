package model;

public class BorrowDetail {
    private int id;
    private int borrowId;
    private int bookId;
    private String borrowDate;
    private String returnDate;
    private String returnStatus;

    public BorrowDetail() {}

    public BorrowDetail(int borrowId, int bookId, String borrowDate, String returnDate, String returnStatus) {
        this.borrowId = borrowId;
        this.bookId = bookId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.returnStatus = returnStatus;
    }

    public BorrowDetail(int id, int borrowId, int bookId, String borrowDate, String returnDate, String returnStatus) {
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

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnStatus() {
        return returnStatus;
    }

    public void setReturnStatus(String returnStatus) {
        this.returnStatus = returnStatus;
    }
}