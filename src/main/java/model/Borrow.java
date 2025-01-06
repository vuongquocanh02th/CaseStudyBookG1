package model;

public class Borrow {
    private int id;
    private int customerId;
    private String customerName;
    private String borrowDate;
    private String returnDate;
    private int bookId;
    private String bookName;

    public Borrow() {}

    public Borrow(int customerId, String borrowDate, String returnDate) {
        this.customerId = customerId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public Borrow(int id, int customerId, String customerName, String borrowDate, String returnDate, int bookId, String bookName) {
        this.id = id;
        this.customerId = customerId;
        this.customerName = customerName;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.bookId = bookId;
        this.bookName = bookName;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }
}