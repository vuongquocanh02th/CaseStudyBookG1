package com.example.qlbook.model;

import java.time.LocalDateTime;

public class Student {
    private int id;
    private String name;
    private String className;
    private String address;
    private LocalDateTime brithDate;
    private Book book;

    public Student() {
    }

    public Student(int id, String name, String className, String address, LocalDateTime brithDate) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.address = address;
        this.brithDate = brithDate;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getBrithDate() {
        return brithDate;
    }

    public void setBrithDate(LocalDateTime brithDate) {
        this.brithDate = brithDate;
    }
}
