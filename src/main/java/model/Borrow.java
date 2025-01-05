
package model;

import java.util.Date;

public class Borrow {
    private int id;
    private int customerId;
    private Date borrowDate;
    private Date returnDate;

    // Constructors
    public Borrow() {}

    public Borrow(int id, int customerId, Date borrowDate, Date returnDate) {
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
}