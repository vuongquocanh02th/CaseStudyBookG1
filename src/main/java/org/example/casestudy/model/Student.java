package org.example.casestudy.model;

import java.util.Date;

public class Student {
    private int id;
    private String studentName;
    private String className;
    private Date dob;

    public Student() {
    }

    public Student(int id, String studentName, String className, Date dob) {
        this.id = id;
        this.studentName = studentName;
        this.className = className;
        this.dob = dob;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

}
