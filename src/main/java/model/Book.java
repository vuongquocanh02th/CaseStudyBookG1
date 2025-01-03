package model;

public class Book {
    private int id;
    private String bookName;
    private String description;
    private String status;
    private Integer genID;
    private Integer publisherID;
    private Integer categoryID;
    private String genreName;
    private String publisherName;
    private String categoryName;

    public Book() {}

    public Book(String bookName, String description, String status, Integer genID, Integer publisherID, Integer categoryID) {
        this.bookName = bookName;
        this.description = description;
        this.status = status;
        this.genID = genID;
        this.publisherID = publisherID;
        this.categoryID = categoryID;
    }

    public Book(int id, String bookName, String description, String status, Integer genID, Integer publisherID, Integer categoryID) {
        this.id = id;
        this.bookName = bookName;
        this.description = description;
        this.status = status;
        this.genID = genID;
        this.publisherID = publisherID;
        this.categoryID = categoryID;
    }

    public Book(String bookName, String description, String status, int categoryID) {
        this.bookName = bookName;
        this.description = description;
        this.status = status;
        this.categoryID = categoryID;
    }

    public Book(int id, String bookName, String description, String status, int categoryID) {
        this.id = id;
        this.bookName = bookName;
        this.description = description;
        this.status = status;
        this.categoryID = categoryID;
    }

    public Book(int id, String bookName, String description, String status, String genreName, String publisherName, String categoryName) {
        this.id = id;
        this.bookName = bookName;
        this.description = description;
        this.status = status;
        this.genreName = genreName;
        this.publisherName = publisherName;
        this.categoryName = categoryName;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getGenID() {
        return genID;
    }

    public void setGenID(Integer genID) {
        this.genID = genID;
    }

    public Integer getPublisherID() {
        return publisherID;
    }

    public void setPublisherID(Integer publisherID) {
        this.publisherID = publisherID;
    }

    public Integer getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Integer categoryID) {
        this.categoryID = categoryID;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}