package services.serviceImpl;

import database.Database;
import models.Library;
import services.LibraryService;

import java.util.List;
import java.util.Scanner;

public class LibraryServiceImpl implements LibraryService {
    @Override
    public String saveLibrary(Library lib) {
        Database.libraryList.add(lib);
        return "Library is saved.";
    }

    @Override
    public List<Library> getAllLibraries() {
        return Database.libraryList;
    }

    @Override
    public Library getLibraryById(Long id) {
        for (Library l : Database.libraryList) {
            if (l.getId() == id) {
                return l;
            }
        }
        return null;
    }

    @Override
    public void updateLibrary(Long id, Library library) {
        for (Library l : Database.libraryList) {
            if (l.getId() == id) {
                l.setName(library.getName());
                l.setAddress(library.getAddress());
            }
        }

    }

    @Override
    public String deleteLibrary(Long id) {
        int index = 0;
        boolean check = false;
        for (Library l : Database.libraryList) {
            if (l.getId() == id) {
                index = Database.libraryList.indexOf(l);
                check = true;
            }
        }
        if (check) {
            Database.libraryList.remove(index);
        }
        return "Library is deleted.";
    }

    public static Library newLib() {
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);
        long id = 0L;
        Library library = new Library();
        for (Library l : Database.libraryList) {
            id = l.getId();
        }
        library.setId(id + 1);
        System.out.println("Give name of library:");
        library.setName(scanner.nextLine());
        System.out.println("Give address of Library:");
        library.setAddress(scanner.nextLine());


        return library;
    }
    public static boolean libIdCheck(long id){
        boolean check=false;
        for(Library l: Database.libraryList){
            if(l.getId()==id){
                check=true;
            }
        }
        return check;
    }

}
