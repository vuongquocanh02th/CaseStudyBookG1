package model;

import java.util.Date;

public class Customer {
    private int id;
    private String name;
    private String schoolName;
    private String address;
    private Date dob;

    // Constructors
    public Customer() {}

    public Customer(int id, String name, String schoolName, String address, Date dob) {
        this.id = id;
        this.name = name;
        this.schoolName = schoolName;
        this.address = address;
        this.dob = dob;
    }

    // Getters and Setters
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

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }
}
