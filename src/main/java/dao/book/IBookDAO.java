package dao.book;

import model.Books;

import java.util.List;

public interface IBookDAO {
    List<Books> getAllBooks();
    boolean addBook(Books book);
    boolean updateBook(Books book);
    boolean deleteBook(int id);
    Books getBookById(int id);
    List<Books> searchBooks(String publisherName, String genreName);
}
