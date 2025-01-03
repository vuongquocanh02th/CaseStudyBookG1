package dao.book;

import model.Book;
import java.util.List;

public interface IBookDAO {
    List<Book> findAll();
    Book findById(int id);
    void save(Book book);
    void update(Book book);
    void delete(int id);
}