package com.example.qlbook.model;

public class Book {
    private String name;
    private String descriptiton;
    private String imageUrl;
    private boolean status;
    private Author author;
    private Genre genre;

    public Book() {
    }

    public Book(String name, String descriptiton, String imageUrl, boolean status, Author author, Genre genre) {
        this.name = name;
        this.descriptiton = descriptiton;
        this.imageUrl = imageUrl;
        this.status = status;
        this.author = author;
        this.genre = genre;
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

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
