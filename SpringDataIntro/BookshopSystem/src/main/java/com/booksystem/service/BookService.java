package com.booksystem.service;

import com.booksystem.model.entity.Book;

import java.util.List;

public interface BookService {
    long count();

    Book save(Book book);

    List<String> getBookTitlesPublishedAfter(int year);

    List<String> getBooksByAuthorOrderedByReleaseDateAndTitle(String fullname);
}
