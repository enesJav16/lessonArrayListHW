package services;

import models.Book;

import java.util.List;

public interface BookService {
    void saveBook(Long libId, Book book);
    List<Book> getAllBookByLibId(Long libId);
    List<Book> getAllBooks();
    Book getBookById(Long bookId);
    String deleteBook(Long bookId);
    void clearBooksByLibraryId(Long libId);
}
