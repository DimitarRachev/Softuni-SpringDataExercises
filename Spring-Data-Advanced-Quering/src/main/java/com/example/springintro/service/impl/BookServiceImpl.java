package com.example.springintro.service.impl;


import com.example.springintro.model.entity.*;
import com.example.springintro.repository.BookRepository;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class BookServiceImpl implements BookService {

  private static final String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";

  private final BookRepository bookRepository;
  private final AuthorService authorService;
  private final CategoryService categoryService;

  @PersistenceContext
  private final EntityManager em;

  public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService,
    EntityManager em) {
    this.bookRepository = bookRepository;
    this.authorService = authorService;
    this.categoryService = categoryService;
    this.em = em;
  }

  @Override
  public void seedBooks() throws IOException {
    if (bookRepository.count() > 0) {
      return;
    }

    Files
      .readAllLines(Path.of(BOOKS_FILE_PATH))
      .forEach(row -> {
        String[] bookInfo = row.split("\\s+");

        Book book = createBookFromInfo(bookInfo);

        bookRepository.save(book);
      });
  }

  @Override
  public List<Book> findAllBooksAfterYear(int year) {
    return bookRepository
      .findAllByReleaseDateAfter(LocalDate.of(year, 12, 31));
  }

  @Override
  public List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year) {
    return bookRepository
      .findAllByReleaseDateBefore(LocalDate.of(year, 1, 1))
      .stream()
      .map(book -> String.format("%s %s", book.getAuthor().getFirstName(),
        book.getAuthor().getLastName()))
      .distinct()
      .collect(Collectors.toList());
  }

  @Override
  public List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName) {
    return bookRepository
      .findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(firstName, lastName)
      .stream()
      .map(book -> String.format("%s %s %d",
        book.getTitle(),
        book.getReleaseDate(),
        book.getCopies()))
      .collect(Collectors.toList());
  }

  @Override public List<String> getAllBooksWithAgeRestriction(AgeRestriction ageRestriction) {
    return bookRepository
      .findAllByAgeRestriction(ageRestriction)
      .stream()
      .map(Book::getTitle)
      .collect(Collectors.toList());
  }

  @Override public List<String> getBookTitlesWithEditionAndCopiesLessThan(EditionType editionType, int maxCopies) {
    return bookRepository
      .findAllByEditionTypeAndCopiesIsLessThan(editionType, maxCopies)
      .stream()
      .map(Book::getTitle)
      .collect(Collectors.toList());
  }

  @Override public List<String> getBookTitlesAndPriceForBookWithPriceNotBetween(int min, int max) {
    return bookRepository
      .findAllByPriceIsLessThanOrPriceGreaterThan(BigDecimal.valueOf(min), BigDecimal.valueOf(max))
      .stream()
      .map(b -> b.getTitle() + " - $" + b.getPrice())
      .collect(Collectors.toList());
  }

  @Override public List<String> getBookTitlesForBooksNotReleasedIn(int year) {
    return bookRepository.findAllByReleaseDateNotEquals(year);
  }

  @Override public List<String> getBookInfoForBooksBefore(LocalDate date) {
    return bookRepository
      .findAllByReleaseDateBefore(date)
      .stream()
      .map(b -> b.getTitle() + " " + b.getEditionType() + " " + b.getPrice())
      .collect(Collectors.toList());
  }

  @Override public List<String> getBookTitlesContaining(String search) {
    return bookRepository
      .findAllByTitleIsContainingIgnoreCase(search)
      .stream()
      .map(Book::getTitle)
      .collect(Collectors.toList());
  }

  @Override public List<String> getBookTitlesWithAuthorFirstNameStartsWith(String firstNameStart) {
    return bookRepository
      .findAllByAuthorFirstNameStartWith(firstNameStart.length(), firstNameStart)
      .stream()
      .map(this::extractTitleAndAuthor)
      .collect(Collectors.toList());
  }

  @Override public int getCountBookWithTitleLongerThan(int length) {
    return bookRepository.countBookByTitleLonger(length);
  }

  @Override public String getBookInfoByTitle(String title) {
    String result = bookRepository.getBookInfoByTitle(title);
    return result == null ? "None found!" : result.replace(",", " ");
  }

  @Override public String increaseBookCopies(LocalDate date, int amount) {
    List<Book> books = bookRepository
      .findAllByReleaseDateAfter(date)
      .stream()
      .peek(b -> b.setCopies(b.getCopies() + amount))
      .peek(bookRepository::save).toList();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM uuuu");
    return String.format("%d books are released after %s, so total of %d book copies were added",
      books.size(), formatter.format(date), books.size() * amount);
  }

  @Override public int removeBooksWithCopiesLessThan(int copies) {
    return bookRepository.deleteAllByCopiesIsLessThan(copies);
  }

  @Override public String getTotalBooks(String firstName, String lastName) {
    int booksCount = (int) em.createNamedStoredProcedureQuery("new_procedure").setParameter("firstName", firstName)
      .setParameter("lastName", lastName).getOutputParameterValue("books_count");
    return String.format("%s %s has written %d books", firstName, lastName, booksCount);
  }

  private String extractTitleAndAuthor(Book book) {
    return book.getTitle() + " (" + book.getAuthor().getFirstName() + " " + book.getAuthor().getLastName() + ")";
  }

  private Book createBookFromInfo(String[] bookInfo) {
    EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
    LocalDate releaseDate = LocalDate
      .parse(bookInfo[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
    Integer copies = Integer.parseInt(bookInfo[2]);
    BigDecimal price = new BigDecimal(bookInfo[3]);
    AgeRestriction ageRestriction = AgeRestriction
      .values()[Integer.parseInt(bookInfo[4])];
    String title = Arrays.stream(bookInfo)
      .skip(5)
      .collect(Collectors.joining(" "));

    Author author = authorService.getRandomAuthor();
    Set<Category> categories = categoryService
      .getRandomCategories();

    return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);

  }
}
