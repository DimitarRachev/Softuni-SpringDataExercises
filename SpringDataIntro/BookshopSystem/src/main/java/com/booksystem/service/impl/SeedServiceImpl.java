package com.booksystem.service.impl;

import com.booksystem.model.entity.Book;
import com.booksystem.model.entity.Category;
import com.booksystem.model.enums.AgeRestriction;
import com.booksystem.service.AuthorService;
import com.booksystem.service.BookService;
import com.booksystem.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.booksystem.model.entity.Author;
import com.booksystem.model.enums.EditionType;
import com.booksystem.service.SeedService;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SeedServiceImpl implements SeedService {

    private static final String RESOURCE_PATH = "BookshopSystem/src/main/resources/files/";
    private static final String AUTHORS_FILE_NAME = RESOURCE_PATH + "authors.txt";
    private static final String CATEGORIES_FILE_NAME = RESOURCE_PATH + "categories.txt";
    private static final String BOOKS_FILE_NAME = RESOURCE_PATH + "books.txt";

    private final BookService bookService;
    private final CategoryService categoryService;
    private final AuthorService authorService;

    @Override
    public void seedAll() throws IOException {
        seedAuthors();
        seedCategories();
        seedBooks();
    }

    private void seedAuthors() throws IOException {
        if (authorService.count() > 0) {
            return;
        }

        Files.readAllLines(Path.of(AUTHORS_FILE_NAME))
                .stream()
                .filter(s -> !s.isBlank())
                .map(s -> s.split("\\s+"))
                .map(SeedServiceImpl::makeAuthor)
                .forEach(authorService::save);
    }

    private static Author makeAuthor(String[] names) {
        return  Author.builder()
                .firstName(names[0])
                .lastName(names[1])
                .build();
    }


    private void seedCategories() throws IOException {
        if (categoryService.count() > 0) {
            return;
        }

        Files.readAllLines(Path.of(CATEGORIES_FILE_NAME))
                .stream()
                .filter(s -> !s.isBlank())
                .map(Category::new)
                .forEach(categoryService::save);
    }

    private void seedBooks() throws IOException {
        if (bookService.count() > 0) {
            return;
        }

        Files.readAllLines(Path.of(BOOKS_FILE_NAME))
                .stream()
                .filter(s -> !s.isBlank())
                .map(this::makeBook)
                .forEach(bookService::save);
    }

    private Book makeBook(String line) {
        String[] bookParts = line.split("\\s+");

        int editionTypeIndex = Integer.parseInt(bookParts[0]);
        EditionType editionType = EditionType.values()[editionTypeIndex];

        LocalDate releaseDate = LocalDate.parse(bookParts[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
        int copies = Integer.parseInt(bookParts[2]);
        BigDecimal price = new BigDecimal(bookParts[3]);

        int ageRestrictionIndex = Integer.parseInt(bookParts[4]);
        AgeRestriction ageRestriction = AgeRestriction.values()[ageRestrictionIndex];

        String title = Arrays.stream(bookParts).skip(5).collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService.getRandomCategories();

        return  Book.builder()
                .title(title)
                .editionType(editionType)
                .price(price)
                .releaseDate(releaseDate)
                .ageRestriction(ageRestriction)
                .author(author)
                .categories(categories)
                .copies(copies)
                .build();
    }
}
