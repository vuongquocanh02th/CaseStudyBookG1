package model;

public class Books {
    private int id;
    private String bookName;
    private String description;
    private String status; // Available, Unavailable, Reserved
    private int genId;
    private int publisherId;
    private int categoryId;

    private Genres genre;
    private Publishers publisher;
    private Categories category;

    // Constructors
    public Books() {
    }

    public Books(int id, String bookName, String description, String status, int genId, int publisherId, int categoryId) {
        this.id = id;
        this.bookName = bookName;
        this.description = description;
        this.status = status;
        this.genId = genId;
        this.publisherId = publisherId;
        this.categoryId = categoryId;
        this.genre = new Genres();
        this.publisher = new Publishers();
        this.category = new Categories();
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

    public int getGenId() {
        return genId;
    }

    public void setGenId(int genId) {
        this.genId = genId;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }


    public Genres getGenre() {
        return genre;
    }

    public void setGenre(Genres genre) {
        this.genre = genre;
    }

    public Publishers getPublisher() {
        return publisher;
    }

    public void setPublisher(Publishers publisher) {
        this.publisher = publisher;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }
}