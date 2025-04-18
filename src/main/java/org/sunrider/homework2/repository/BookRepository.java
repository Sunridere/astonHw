package org.sunrider.homework2.repository;

import lombok.extern.slf4j.Slf4j;
import org.sunrider.homework2.entity.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class BookRepository {
    private static final String USER_NAME = "root";
    private static final String PASSWORD = "1234";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/rest-api";

    private Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e) {
            log.error("Driver class not found: " + e.getMessage());
        }
        return DriverManager.getConnection(CONNECTION_URL, USER_NAME, PASSWORD);
    }

    public Book getById(int id) throws SQLException {

        try (Connection connection = getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM books WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setAuthor(resultSet.getString("author"));
                return book;
            }
            return null;
        }

    }

    public List<Book> getAll() throws SQLException {

        List<Book> books = new ArrayList<>();

        try (Connection connection = getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM books");
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setAuthor(resultSet.getString("author"));
                books.add(book);
            }
            return books;
        }

    }

    public boolean addBook(Book book) throws SQLException {
        try (Connection connection = getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO books (name, author) VALUES (?, ?)");
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getAuthor());
            return preparedStatement.executeUpdate() > 0;
        }
    }

    public boolean updateBook(Book book) throws SQLException {
        try (Connection connection = getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE books SET name = ?, author = ? WHERE id = ?");
            preparedStatement.setString(1, book.getName());
            preparedStatement.setString(2, book.getAuthor());
            preparedStatement.setInt(3, book.getId());
            return preparedStatement.executeUpdate() > 0;
        }
    }

    public boolean deleteBook(int id) throws SQLException {
        try (Connection connection = getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM books WHERE id = ?");
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate() > 0;
        }
    }
}
