package org.example.casestudy.model;

public class Book {
    private int id;
    private String title;
    private String image;
    private boolean state;
    private Genre genre;
    private Publisher publisher;
    private BookCategory category;

    public Book() {
    }

    public Book(int id, String title, String image, boolean state, Genre genre, Publisher publisher, BookCategory category) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.state = state;
        this.genre = genre;
        this.publisher = publisher;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public BookCategory getCategory() {
        return category;
    }

    public void setCategory(BookCategory category) {
        this.category = category;
    }
}
