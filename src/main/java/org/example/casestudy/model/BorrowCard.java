package org.example.casestudy.model;

import java.util.Date;

public class BorrowCard {
    private int id;
    private Student student;
    private Book book;
    private Date borrowDate;
    private Date returnDate;
    private BorrowState borrowState;

    public BorrowCard() {
    }

    public BorrowCard(int id, Student student, Book book, Date borrowDate, Date returnDate, BorrowState borrowState) {
        this.id = id;
        this.student = student;
        this.book = book;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.borrowState = borrowState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
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

    public BorrowState getBorrowState() {
        return borrowState;
    }

    public void setBorrowState(BorrowState borrowState) {
        this.borrowState = borrowState;
    }
}

