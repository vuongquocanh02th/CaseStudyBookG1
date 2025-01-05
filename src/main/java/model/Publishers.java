package model;

public class Publishers {
    private int publisherId;
    private String name;

    // Constructors
    public Publishers() {}

    public Publishers(int publisherId, String name) {
        this.publisherId = publisherId;
        this.name = name;
    }

    // Getters and Setters
    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}