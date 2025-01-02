package service.book;

import model.Book;
import java.util.List;

public interface IBookService {
    List<Book> getAllBooks();
    Book getBookById(int id);
    void addBook(Book book);
    void updateBook(Book book);
    void deleteBook(int id);
}