package model;

public class Borrow {
    private int id;
    private int customerId;
    private String borrowDate;
    private String returnDate;

    public Borrow() {}

    public Borrow(int customerId, String borrowDate, String returnDate) {
        this.customerId = customerId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }

    public Borrow(int id, int customerId, String borrowDate, String returnDate) {
        this.id = id;
        this.customerId = customerId;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
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
}