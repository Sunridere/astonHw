package org.sunrider.homework2.mapper;

import org.sunrider.homework2.dto.BookDTO;
import org.sunrider.homework2.entity.Book;

public class BookMapper {

    public static BookDTO toBookDTO(Book book) {

        if (book == null) return null;

        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setName(book.getName());
        bookDTO.setAuthor(book.getAuthor());
        return bookDTO;
    }

    public static Book toBook(BookDTO bookDTO) {

        if (bookDTO == null) return null;

        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setName(bookDTO.getName());
        book.setAuthor(bookDTO.getAuthor());
        return book;
    }

}
