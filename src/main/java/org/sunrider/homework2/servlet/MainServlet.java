package org.sunrider.homework2.servlet;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.sunrider.homework2.dto.BookDTO;
import org.sunrider.homework2.repository.BookRepository;
import org.sunrider.homework2.service.BookService;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

@Slf4j
@WebServlet("/main/*")
public class MainServlet extends HttpServlet {

    private BookService bookService;
    private ObjectMapper objectMapper;

    @Override
    public void init() throws ServletException {
        bookService = new BookService(new BookRepository());
        objectMapper = new ObjectMapper();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pathInfo = req.getPathInfo();
        PrintWriter out = resp.getWriter();

        try {
            if (pathInfo == null || pathInfo.equals("/")) {
                List<BookDTO> bookDTOS = bookService.getAllBooks();
                objectMapper.writeValue(out, bookDTOS);

            }
            else {
                BookDTO bookDTO = bookService.getBookById(Integer.parseInt(pathInfo.substring(1)));
                objectMapper.writeValue(resp.getWriter(), bookDTO);
            }
        }catch (SQLException e){
            log.error("SQL error during GET: " + e.getMessage());
        }
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BookDTO bookDTO = objectMapper.readValue(req.getReader(), BookDTO.class);
        try {
            bookService.addBook(bookDTO);
        }catch (SQLException e){
            log.error("SQL error during POST: " + e.getMessage());
        }

    }

    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        BookDTO bookDTO = objectMapper.readValue(req.getReader(), BookDTO.class);
        try {
            bookService.updateBook(bookDTO);
        }catch (SQLException e){
            log.error("SQL error during PATCH: " + e.getMessage());
        }

    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getPathInfo().substring(1);
        if (id != null){
            try {
                bookService.deleteBook(Integer.parseInt(id));
            }catch (SQLException e){
                log.error("SQL error during DELETE: " + e.getMessage());
            }
        }

    }
}
