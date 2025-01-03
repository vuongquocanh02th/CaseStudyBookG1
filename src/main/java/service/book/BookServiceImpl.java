package service.book;

import dao.book.IBookDAO;
import dao.book.BookDAOImpl;
import model.Book;

import java.util.List;

public class BookServiceImpl implements IBookService {
    private IBookDAO bookDAO = new BookDAOImpl();

    @Override
    public List<Book> findAll() {
        return bookDAO.findAll();
    }

    @Override
    public Book findById(int id) {
        return bookDAO.findById(id);
    }

    @Override
    public void save(Book book) {
        bookDAO.save(book);
    }

    @Override
    public void update(Book book) {
        bookDAO.update(book);
    }

    @Override
    public void delete(int id) {
        bookDAO.delete(id);
    }
}