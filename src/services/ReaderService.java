package services;

import models.Reader;

import java.util.List;

public interface ReaderService {
    void saveReader(Reader reader);
    List<Reader> getAllReaders();
    Reader getReaderById(Long id);
    String updateReader(Long id, Reader reader);
    void assignReaderToLibrary(Long readerId, Long LibraryId);
}
