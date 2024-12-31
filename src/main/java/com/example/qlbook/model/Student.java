package com.example.qlbook.model;

import java.sql.Time;
import java.time.LocalDateTime;

public class Student {
    private int id;
    private String name;
    private String className;
    private String address;
    private Time brithDate;


    public Student() {
    }

    public Student(int id, String name, String className, String address, Time brithDate) {
        this.id = id;
        this.name = name;
        this.className = className;
        this.address = address;
        this.brithDate = brithDate;
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

    public Time getBrithDate() {
        return brithDate;
    }

    public void setBrithDate(Time brithDate) {
        this.brithDate = brithDate;
    }
}
