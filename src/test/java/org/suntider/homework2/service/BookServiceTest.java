package org.suntider.homework2.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.sunrider.homework2.dto.BookDTO;
import org.sunrider.homework2.entity.Book;
import org.sunrider.homework2.repository.BookRepository;
import org.sunrider.homework2.service.BookService;

import java.util.List;

class BookServiceTest {

    private BookService bookService;
    private BookRepository bookRepository;

    @BeforeEach
    void setUp(){
        bookRepository = Mockito.mock(BookRepository.class);
        bookService = new BookService(bookRepository);
    }

    @Test
    void testGetBookById() throws Exception {

        Book book = new Book();
        book.setId(1);
        book.setName("Test Book");
        book.setAuthor("Test Author");

        Mockito.when(bookRepository.getById(1)).thenReturn(book);
        BookDTO bookDTO = bookService.getBookById(1);

        Assertions.assertEquals(book.getName(), bookDTO.getName());
        Assertions.assertEquals(book.getAuthor(), bookDTO.getAuthor());

    }

    @Test
    void testGetAllBooks() throws Exception {

        Book book = new Book();
        book.setId(1);
        book.setName("Test Book");
        book.setAuthor("Test Author");

        Book book2 = new Book();
        book2.setId(2);
        book2.setName("Test Book2");
        book2.setAuthor("Test Author2");

        Mockito.when(bookRepository.getAll()).thenReturn(List.of(book, book2));

        List<BookDTO> bookDTOList = bookService.getAllBooks();

        Assertions.assertEquals(2, bookDTOList.size());
    }

    @Test
    void testAddBook() throws Exception {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setName("Test Book");
        bookDTO.setAuthor("Test Author");

        Mockito.when(bookRepository.addBook(Mockito.any(Book.class))).thenReturn(true);

        Assertions.assertEquals(true, bookService.addBook(bookDTO));

    }

    @Test
    void testUpdateBook() throws Exception {

        BookDTO bookDTO = new BookDTO();
        bookDTO.setName("Test Book");
        bookDTO.setAuthor("Test Author");

        Mockito.when(bookRepository.updateBook(Mockito.any(Book.class))).thenReturn(true);

        Assertions.assertEquals(true, bookService.updateBook(bookDTO));
    }


    @Test
    void testDeleteBook() throws Exception {

        Mockito.when(bookRepository.deleteBook(1)).thenReturn(true);
        Assertions.assertEquals(true, bookService.deleteBook(1));

    }





}
