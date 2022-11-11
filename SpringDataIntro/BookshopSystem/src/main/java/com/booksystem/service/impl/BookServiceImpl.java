package com.booksystem.service.impl;

import com.booksystem.model.entity.Author;
import com.booksystem.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.booksystem.model.entity.Book;
import com.booksystem.repository.BookRepository;
import com.booksystem.service.BookService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Override
    public long count() {
        return bookRepository.count();
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public List<String> getBookTitlesPublishedAfter(int year) {
        return bookRepository.findByReleaseDateAfter(LocalDate.of(year, 12, 31))
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getBooksByAuthorOrderedByReleaseDateAndTitle(String fullname) {
        String[] name = fullname.split("\\s+");
        Author author = authorRepository.findByFirstNameAndLastName(name[0], name[1]).orElseThrow(RuntimeException::new);
        return bookRepository
                .findAllByAuthorOrderByReleaseDateDescTitleAsc(author)
                .stream()
                .map(b -> b.getTitle() + " " + b.getReleaseDate() + " " + b.getCopies())
                .collect(Collectors.toList());
    }

}
