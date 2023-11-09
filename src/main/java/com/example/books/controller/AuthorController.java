package com.example.books.controller;

import com.example.books.model.Author;
import com.example.books.service.AuthorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }


    // Add Author
    @PostMapping("/add-author")
    public Author addAuthor(@RequestBody Author author) throws Exception {
        return authorService.addAuthor(author);
    }


    // Get Author by ID
    @PostMapping("/author-id")
    public Author getAuthorById(@RequestBody Integer id) throws Exception {
        return authorService.getAuthorById(id);
    }


    // Delete Author by ID
    @PostMapping("/delete-author")
    public String deleteAuthor(@RequestBody Integer id) throws Exception {
        authorService.deleteAuthor(id);

        return "Successfully deleted Author by id: " + id;
    }


    // Update Author by ID
    @PostMapping("/change-author")
    public Author getAuthorById(@RequestBody Map<String, Object> data) throws Exception {
        return authorService.changeAuthorById(
                (Integer) data.get("id"), (String) data.get("newName"),
                (String) data.get("newSurname"), (String) data.get("newEmail")
        );
    }
}
