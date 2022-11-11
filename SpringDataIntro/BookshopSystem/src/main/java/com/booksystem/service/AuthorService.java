package com.booksystem.service;

import com.booksystem.model.entity.Author;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AuthorService {
    long count();

    Author save(Author author);

    Author getRandomAuthor();

    List<String> findAuthorNamesPublishedBefore(int year);


    List<String> findAuthorsNameAndBookCountOrderedByBookCount();
}
