package service.book;

import dao.book.IBookDAO;
import dao.book.BookDAOImpl;
import model.Book;
import java.util.List;

public class BookServiceImpl implements IBookService {
    private IBookDAO bookDAO = new BookDAOImpl();

    @Override
    public List<Book> getAllBooks() {
        return bookDAO.getAllBooks();
    }

    @Override
    public Book getBookById(int id) {
        return bookDAO.getBookById(id);
    }

    @Override
    public void addBook(Book book) {
        bookDAO.addBook(book);
    }

    @Override
    public void updateBook(Book book) {
        bookDAO.updateBook(book);
    }

    @Override
    public void deleteBook(int id) {
        bookDAO.deleteBook(id);
    }
}