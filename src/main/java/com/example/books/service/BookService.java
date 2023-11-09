package com.example.books.service;

import com.example.books.model.Author;
import com.example.books.model.Book;
import com.example.books.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookService {
    final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public Book addBook(Book book) throws Exception {
        if (book == null) {
            throw new Exception("Book can not be null");
        }

        return bookRepository.save(book);
    }

    public void deleteBook(String title) throws Exception {
        if (title == null) {
            throw new Exception("Title can not be null");
        }

        Book book = getBookByTitle(title);
        bookRepository.deleteById(book.getId());
    }


    public Book getBookByTitle(String title) throws Exception {
        if (title == null) {
            throw new Exception("Title can not be null");
        }

        Optional<Book> optionalBook = bookRepository.findByTitle(title);

        if (optionalBook.isEmpty()) {
            throw new Exception("No Book found by title: " + title);
        } else {
            return optionalBook.get();
        }
    }


    public Book changeBookByTitle(String title, String newTitle,
                                  Integer newPrice, Author newAuthor) throws Exception {
        if (title == null) {
            throw new Exception("Title can not be null");
        }

        Book book = getBookByTitle(title);

        if (newTitle != null) {
            book.setTitle(newTitle);
        }
        if (newPrice != null) {
            book.setPrice(newPrice);
        }
        if (newAuthor != null) {
            book.setAuthor(newAuthor);
        }

        return bookRepository.saveAndFlush(book);
    }

}
