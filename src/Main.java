import database.Database;
import enums.Gender;
import enums.Genre;
import models.Book;
import models.Library;
import models.Reader;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Book book1=new Book(1L,"Book1","Book1 Author", Genre.DRAMA);
        Book book2=new Book(2L,"Book2","Book2 Author", Genre.DETECTIVE);
        Book book3=new Book(3L,"Book3","Book3 Author", Genre.SCI_FI);
        Book book4=new Book(4L,"Book4","Book4 Author", Genre.ROMANCE);
        Book book5=new Book(5L,"Book5","Book5 Author", Genre.DRAMA);
        Database.bookList.add(book1);
        Database.bookList.add(book2);
        Database.bookList.add(book3);
        Database.bookList.add(book4);
        Database.bookList.add(book5);

        Reader reader1=new Reader(1L,"Reader One"  ,"Reader1@gmail","1111111111", Gender.FEMALE);
        Reader reader2=new Reader(2L,"Reader Two"  ,"Reader2@gmail","2222222222", Gender.FEMALE);
        Reader reader3=new Reader(3L,"Reader Three","Reader3@gmail","3333333333", Gender.FEMALE);
        Reader reader4=new Reader(4L,"Reader Four" ,"Reader4@gmail","4444444444", Gender.MALE);
        Reader reader5=new Reader(5L,"Reader Five" ,"Reader5@gmail","5555555555", Gender.MALE);
        Database.readerList.add(reader1);
        Database.readerList.add(reader2);
        Database.readerList.add(reader3);
        Database.readerList.add(reader4);
        Database.readerList.add(reader5);

        Library library1=new Library(1L,"Library one","Library one address",new ArrayList<Book>(List.of(Database.bookList.get(0),Database.bookList.get(1))),new ArrayList<Reader>(List.of(reader1,reader2,reader3)));
        Library library2=new Library(2L,"Library two","Library two address",new ArrayList<Book>(List.of(Database.bookList.get(2),Database.bookList.get(3),Database.bookList.get(4))),new ArrayList<Reader>(List.of(reader4,reader5)));
        Database.libraryList.add(library1);
        Database.libraryList.add(library2);

        Start.start();
    }
}