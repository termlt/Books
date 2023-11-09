package com.example.books.controller;

import com.example.books.model.Author;
import com.example.books.model.Book;
import com.example.books.service.BookService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    // Get Book by Title
    @PostMapping("/get-book")
    public Book getBookByTitle(@RequestBody Map<String, String> data) throws Exception {
        return bookService.getBookByTitle(data.get("title"));
    }


    // Add Book
    @PostMapping("/add-book")
    public Book addBook(@RequestBody Book book) throws Exception {
        return bookService.addBook(book);
    }


    // Delete Book
    @PostMapping("/delete-book")
    public String deleteBook(@RequestBody Map<String, String> data) throws Exception {
        bookService.deleteBook(data.get("title"));

        return "Successfully deleted Book by title: " + data.get("title");
    }


    // Change Book by Title
    @PostMapping("/change-book")
    public Book changeBookByTitle(@RequestBody Map<String, Object> data) throws Exception {
        return bookService.changeBookByTitle(
                (String) data.get("title"),
                (String) data.get("newTitle"),
                (Integer) data.get("newPrice"),
                (Author) data.get("newAuthor")

        );
    }
}
