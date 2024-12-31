package com.example.qlbook.model;

public class BorrowDetail {
    private int id;
    private int borrow_id;
    private int book_id;


    public BorrowDetail() {
    }

    public BorrowDetail(int id, int borrow_id, int book_id) {
        this.id = id;
        this.borrow_id = borrow_id;
        this.book_id = book_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBorrow_id() {
        return borrow_id;
    }

    public void setBorrow_id(int borrow_id) {
        this.borrow_id = borrow_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }
}
