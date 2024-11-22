import database.Database;
import models.Library;
import services.BookService;
import services.serviceImpl.BookServiceImpl;
import services.serviceImpl.LibraryServiceImpl;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Start {
    public static void start() throws InputMismatchException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("Chose action:");
                System.out.println("""
                        1.Library controls.
                        2.Book controls.
                        3.Reader controls.
                        """);
                int input = scanner.nextInt();
                switch (input) {
                    case 1 ->libStart();
                    case 2 ->bookStart();
                }
            }catch (InputMismatchException e){
                System.out.println("Give correct format.");
                scanner.nextLine();
            }
        }
    }

    static void libStart() throws InputMismatchException {
        LibraryServiceImpl libraryService = new LibraryServiceImpl();
        Scanner scanner = new Scanner(System.in);

        boolean check = true;
        while (check) {
            try {
                System.out.println("What do you want to do:");
                System.out.println("""
                        1.Save Library.
                        2.Get All Libraries.
                        3.Get Library by ID.
                        4.Update Library.
                        5.Delete Library.
                        0.Go to main.
                        """);
                int input = scanner.nextInt();
                switch (input) {
                    case 1 -> libraryService.saveLibrary(LibraryServiceImpl.newLib());
                    case 2 -> System.out.println(libraryService.getAllLibraries());
                    case 3 -> {
                        System.out.println("Give which id:");
                        System.out.println(libraryService.getLibraryById(scanner.nextLong()));
                    }
                    case 4 -> {
                        System.out.println("Give which id:");
                        long idInput=scanner.nextLong();
                        boolean check1=false;
                        for(Library l: Database.libraryList){
                            if(l.getId()==idInput){
                                check1=true;
                            }
                        }
                        if(check1) {
                            libraryService.updateLibrary(idInput, LibraryServiceImpl.newLib());
                        }else{
                            System.out.println("There is no library with such id:");
                        }
                    }
                    case 5 -> {
                        System.out.println("Give which id:");
                        System.out.println(libraryService.deleteLibrary(scanner.nextLong()));
                    }
                    case 0 -> check = false;
                    default -> check=false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Give correct format.");
                scanner.nextLine();
            }
        }
    }
    static void bookStart() throws InputMismatchException{
        BookService bookService=new BookServiceImpl();
        Scanner scanner = new Scanner(System.in);

        boolean check = true;
        while (check) {
            try{
                System.out.println("What do you want want to do:");
                System.out.println("""
                        1.Save book.
                        2.Get All Books By Lib ID.
                        3.Get All Books.
                        4.Get Book by ID.
                        5.Delete Book
                        6.Clear Books By Lib ID.
                        0.Go to main.
                        """);
                int input=scanner.nextInt();
                switch (input){
                    case 1-> {
                        System.out.println("Give which lib id:");
                        long idInput=scanner.nextLong();
                        boolean check1=false;
                        for(Library l: Database.libraryList){
                            if(l.getId()==idInput){
                                check1=true;
                            }
                        }
                        if(check1) {
                            bookService.saveBook(idInput,BookServiceImpl.newBook());
                        }else{
                            System.out.println("There is no library with such id:");
                        }
                    }
                    case 2->{
                        System.out.println("Give which id:");
                        long idInput=scanner.nextLong();
                        if(LibraryServiceImpl.libIdCheck(idInput)){
                            System.out.println(bookService.getAllBookByLibId(idInput));
                        }else{
                            System.out.println("There is no library with such id:");
                        }
                    }
                    case 3-> System.out.println(bookService.getAllBooks());
                    case 4-> {
                        System.out.println("Give which id:");
                        long idInput=scanner.nextLong();
                        if(BookServiceImpl.checkBookId(idInput)){
                            System.out.println(bookService.getBookById(idInput));
                        }else{
                            System.out.println("There is no book with such id:");
                        }
                    }
                    case 5->{
                        System.out.println("Give which id:");
                        long idInput=scanner.nextLong();
                        if(BookServiceImpl.checkBookId(idInput)){
                            System.out.println(bookService.deleteBook(idInput));
                        }else{
                            System.out.println("There is no book with such id:");
                        }
                    }
                    case 6->{
                        System.out.println("Give which lib id:");
                        long idInput=scanner.nextLong();
                        if(LibraryServiceImpl.libIdCheck(idInput)){
                            bookService.clearBooksByLibraryId(idInput);
                        }else{
                            System.out.println("There is no such library with such id.");
                        }
                    }
                    case 0->check=false;
                    default -> check=false;
                }
            }catch (InputMismatchException e){
                System.out.println("Give correct format.");
                scanner.nextLine();
            }
        }
    }
}
