package com.example.qlbook.model;

public class Book {
    private int id;
    private String name;
    private String descriptiton;
    private String imageUrl;
    private boolean status;
    private int author_id;
    private int genre_id;


    public Book() {
    }

    public Book(int id, String name, String descriptiton, String imageUrl, boolean status, int author_id, int genre_id) {
        this.id = id;
        this.name = name;
        this.descriptiton = descriptiton;
        this.imageUrl = imageUrl;
        this.status = status;
        this.author_id = author_id;
        this.genre_id = genre_id;
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

    public String getDescriptiton() {
        return descriptiton;
    }

    public void setDescriptiton(String descriptiton) {
        this.descriptiton = descriptiton;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }
}
