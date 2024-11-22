package services.serviceImpl;

import database.Database;
import enums.Genre;
import models.Book;
import models.Library;
import services.BookService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BookServiceImpl implements BookService {
    @Override
    public void saveBook(Long libId, Book book) {
        for (Library l : Database.libraryList) {
            if (l.getId() == libId) {
                l.getBooks().add(book);
            }
        }
    }

    @Override
    public List<Book> getAllBooks() {
        return Database.bookList;
    }

    @Override
    public List<Book> getAllBookByLibId(Long libId) {
        if (Database.libraryList.size() > libId || libId < 0) {
            System.out.println("There is no library with such id.");
        } else {
            for (Library l : Database.libraryList) {
                if (l.getId() == libId) {
                    return l.getBooks();
                }
            }
        }
        return null;
    }

    @Override
    public Book getBookById(Long bookId) {
        for (Book b : Database.bookList) {
            if (b.getId() == bookId) {
                return b;
            }
        }
        return null;
    }

    @Override
    public String deleteBook(Long bookId) {
        int index = -1;
        boolean check = false;
        for (Book b : Database.bookList) {
            if (b.getId() == bookId) {
                index = Database.bookList.indexOf(b);
                check = true;
            }
        }
        if (check && index>-1) {
            Database.bookList.remove(index);
        }

        int index1 = -1;
        boolean check1 = false;
        for(Library l:Database.libraryList){
            if(l.getBooks()==null){
                continue;
            }
            for(Book b:l.getBooks()){
                if(b.getId()==bookId) {
                    index1 = l.getBooks().indexOf(b);
                    check1 = true;
                }
            }
            if (check1 && index1>-1) {
                l.getBooks().remove(index1);
            }
        }




        return "Book is Deleted!";
    }

    @Override
    public void clearBooksByLibraryId(Long libId) {
        for (Library l : Database.libraryList) {
            if (l.getId() == libId) {
                Database.bookList.removeAll(l.getBooks());
                l.setBooks(null);
            }
        }
        System.out.println("All books are deleted.");
    }

    public static Book newBook() throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        Book newBook = new Book();
        Long newId = 0L;
        for (Book b : Database.bookList) {
            newId = b.getId();
        }
        newBook.setId(newId+1);

        System.out.println("Give Name:");
        newBook.setName(scanner.nextLine());

        System.out.println("Give authors name:");
        newBook.setAuthor(scanner.nextLine());

        System.out.println("Give main genre:");

        while (true) {
            try {
                String genreInput = scanner.nextLine();
                boolean genreFound = false;

                for (Genre genre : Genre.values()) {
                    if (genreInput.equalsIgnoreCase(genre.name())) {
                        newBook.setGenre(genre);
                        genreFound = true;
                        break;
                    }
                }
                if (!genreFound) {
                    throw new RuntimeException("There is no such genre in the database.");
                }
                break;
            } catch (RuntimeException e) {
                System.out.println(e.getMessage());
            }
        }
        Database.bookList.add(newBook);
        return newBook;
    }

    public static boolean checkBookId(long id) {
        boolean check = false;
        for (Book b : Database.bookList) {
            if (b.getId() == id) {
                check = true;
            }
        }
        return check;
    }
}
