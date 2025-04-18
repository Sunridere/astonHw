package org.sunrider.homework2.service;

import org.sunrider.homework2.dto.BookDTO;
import org.sunrider.homework2.entity.Book;
import org.sunrider.homework2.mapper.BookMapper;
import org.sunrider.homework2.repository.BookRepository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public BookDTO getBookById(int id) throws SQLException {
        return BookMapper.toBookDTO(bookRepository.getById(id));
    }

    public List<BookDTO> getAllBooks() throws SQLException {
        List<Book> books = bookRepository.getAll();
        List<BookDTO> bookDTOs = new ArrayList<>();
        for (Book book : books) {
            bookDTOs.add(BookMapper.toBookDTO(book));
        }
        return bookDTOs;
    }

    public boolean addBook(BookDTO bookDTO) throws SQLException {
        Book book = BookMapper.toBook(bookDTO);
        return bookRepository.addBook(book);
    }

    public boolean updateBook(BookDTO bookDTO) throws SQLException {
        Book book = BookMapper.toBook(bookDTO);
        return bookRepository.updateBook(book);
    }

    public boolean deleteBook(int id) throws SQLException {
        return bookRepository.deleteBook(id);
    }

}
