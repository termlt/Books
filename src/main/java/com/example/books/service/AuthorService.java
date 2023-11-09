package com.example.books.service;

import com.example.books.model.Author;
import com.example.books.repository.AuthorRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorService {
    final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author addAuthor(Author author) throws Exception {
        if (author == null) {
            throw new Exception("Author can not be null");
        }

        return authorRepository.save(author);
    }


    public void deleteAuthor(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("ID can not be null");
        }

        authorRepository.deleteById(id);
    }


    public Author getAuthorById(Integer id) throws Exception {
        if (id == null) {
            throw new Exception("ID can not be null");
        }

        Optional<Author> optionalAuthor = authorRepository.findById(id);

        if (optionalAuthor.isEmpty()) {
            throw new Exception("No Author found by this ID");
        } else {
            return optionalAuthor.get();
        }
    }


    public Author changeAuthorById(Integer id, String newName,
                                   String newSurname, String newEmail) throws Exception {
        if (id == null) {
            throw new Exception("ID can not be null");
        }

        Optional<Author> optionalAuthor = authorRepository.findById(id);

        if (optionalAuthor.isPresent()) {
            Author author = optionalAuthor.get();

            if (newName != null) {
                author.setName(newName);
            }
            if (newSurname != null) {
                author.setSurname(newSurname);
            }
            if (newEmail != null) {
                author.setEmail(newEmail);
            }

            return authorRepository.saveAndFlush(author);
        } else {
            throw new Exception("Author was not found for ID: " + id);
        }
    }
}
