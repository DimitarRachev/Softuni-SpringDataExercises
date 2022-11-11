package com.booksystem.controller;

import com.booksystem.service.AuthorService;
import com.booksystem.service.BookService;
import com.booksystem.service.SeedService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ConsoleRunner implements CommandLineRunner {

    private final SeedService seedService;
    private final BookService bookService;
    private final AuthorService authorService;


    @Override
    public void run(String... args) throws Exception {
        seedService.seedAll();
//        booksAfter(2000);
//        authorNamesWithBookBefore(1990);
//        authorsNameAndBookCountOrderedByBookCount();
//        getBooksByAuthorOrderedByReleaseDateAndTitle("George Powell");
    }

    private void booksAfter(int year) {
       List<String> bookNames = bookService.getBookTitlesPublishedAfter(year);
        System.out.println(String.join(System.lineSeparator(), bookNames));
    }

    private void authorNamesWithBookBefore(int year) {
        List<String> authorNames = authorService.findAuthorNamesPublishedBefore(year);
        System.out.println(String.join(System.lineSeparator(), authorNames));
    }

    private void authorsNameAndBookCountOrderedByBookCount(){
        List<String> resultList = authorService.findAuthorsNameAndBookCountOrderedByBookCount();
        System.out.println(String.join(System.lineSeparator(), resultList));
    }

    private void getBooksByAuthorOrderedByReleaseDateAndTitle(String fullname) {
        List<String> resultList = bookService.getBooksByAuthorOrderedByReleaseDateAndTitle(fullname);
        System.out.println(String.join(System.lineSeparator(), resultList));
    }
}
