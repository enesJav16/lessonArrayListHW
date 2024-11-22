package services;

import models.Library;

import java.util.List;

public interface LibraryService {
    String saveLibrary(Library lib);
    List<Library>getAllLibraries();
    Library getLibraryById(Long id);
    void updateLibrary(Long id,Library library);
    String deleteLibrary(Long id);
}
