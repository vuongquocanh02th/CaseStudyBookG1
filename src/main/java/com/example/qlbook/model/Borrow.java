package com.example.qlbook.model;

import java.sql.Time;

public class Borrow {
    private int id;
    private int student_id;
    private Time dateTime;


    public Borrow() {
    }

    public Borrow(int id, int student_id, Time dateTime) {
        this.id = id;
        this.student_id = student_id;
        this.dateTime = dateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public Time getDateTime() {
        return dateTime;
    }

    public void setDateTime(Time dateTime) {
        this.dateTime = dateTime;
    }
}
