package com.example.springintro;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

@Component

public class CommandLineRunnerImpl implements CommandLineRunner {

  private final CategoryService categoryService;
  private final AuthorService authorService;
  private final BookService bookService;
  private final Scanner scanner;

  public CommandLineRunnerImpl(CategoryService categoryService, AuthorService authorService, BookService bookService,
    Scanner scanner) {
    this.categoryService = categoryService;
    this.authorService = authorService;
    this.bookService = bookService;
    this.scanner = scanner;
  }

  @Override
  public void run(String... args) throws Exception {
    seedData();

    //printAllBooksAfterYear(2000);
    //        printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(1990);
    //   printAllAuthorsAndNumberOfTheirBooks();
    //        printALlBooksByAuthorNameOrderByReleaseDate("George", "Powell");

    //               New methods
    //    printBookTitlesByAgeRestriction();
    //    printBookTitlesWithEditionAndCopiesLessThan(EditionType.GOLD, 5000);
    //    printBookTitlesAndPriceForBookWithPriceNotBetween(5, 40);
    //    printBookTitlesForBooksNotReleasedIn(2000);
    //    printBookInfoForBooksBefore();
    //    printAuthorNamesWhereFirstNameEndsLike();
    //    printBookTitlesContaining();
    //    printBookTitlesWithAuthorFirstNameStartsWith();
    //    printCountBookWithTitleLongerThan();
    //    printAuthorsWithBookCountOrderByBookCount();
    //    printBookInfoByTitle();
    //    increaseBookCopies();
    //    removeBooksWithCopiesLessThan();
    //    storedProcedure();
  }

  private void storedProcedure() {
    //    Execute procedure in sql and don't forget the delimiters
    //    CREATE DEFINER=`root`@`localhost` PROCEDURE `new_procedure`(IN firstName VARCHAR(45), IN lastName VARCHAR(45), OUT books_count INT)
    //    BEGIN
    //    SELECT COUNT(a.id) INTO books_count FROM authors AS a
    //    JOIN books AS b
    //    ON b.author_id = a.id
    //    WHERE a.first_name = firstName AND a.last_name = lastName;
    //    GROUP BY a.id
    //
    //    END

    System.out.println("Enter author's first and last name:");
    String[] names = scanner.nextLine().split("\\s+");
    System.out.println("Total number of books:" + bookService.getTotalBooks(names[0], names[1]));
  }

  private void removeBooksWithCopiesLessThan() {
    System.out.println("Enter number of copies:");
    int copies = Integer.parseInt(scanner.nextLine());
    System.out.println("Books deleted: " + bookService.removeBooksWithCopiesLessThan(copies));
  }

  private void increaseBookCopies() {
    System.out.println("Enter year:");
    LocalDate date = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd MMM uuuu"));
    System.out.println("Enter amount to increase");
    int amount = Integer.parseInt(scanner.nextLine());
    System.out.println(bookService.increaseBookCopies(date, amount));
  }

  private void printBookInfoByTitle() {
    System.out.println("Enter book title:");
    String title = scanner.nextLine();
    System.out.println(bookService.getBookInfoByTitle(title));
  }

  private void printAuthorsWithBookCountOrderByBookCount() {
    List<String> resultList = authorService.getAuthorsWithBookCountOrderByBookCount();
    System.out.println(String.join(System.lineSeparator(), resultList));
  }

  private void printCountBookWithTitleLongerThan() {
    System.out.println("Enter min title length(not included) :");
    int length = Integer.parseInt(scanner.nextLine());
    System.out.println(bookService.getCountBookWithTitleLongerThan(length));
  }

  private void printBookTitlesWithAuthorFirstNameStartsWith() {
    System.out.println("Enter string to search for:");
    String firstNameStart = scanner.nextLine();
    List<String> bookNames = bookService.getBookTitlesWithAuthorFirstNameStartsWith(firstNameStart);
    String noResult = "No results found for string " + firstNameStart;
    String result = String.join(System.lineSeparator(), bookNames);
    System.out.println(bookNames.isEmpty() ? noResult : result);
  }

  private void printBookTitlesContaining() {
    System.out.println("Enter string to search for:");
    String search = scanner.nextLine();
    List<String> bookNames = bookService.getBookTitlesContaining(search);
    String noResult = "No results found for string " + search;
    String result = String.join(System.lineSeparator(), bookNames);
    System.out.println(bookNames.isEmpty() ? noResult : result);
  }


  private void printAuthorNamesWhereFirstNameEndsLike() {
    System.out.println("Enter the end of the author's first name:");
    String nameEnding = scanner.nextLine();
    List<String> authorNames = authorService.getAuthorNamesWhereFirstNameEndsLike(nameEnding);
    String noResult = "No authors found with first name ending on " + nameEnding;
    String result = String.join(System.lineSeparator(), authorNames);
    System.out.println(authorNames.isEmpty() ? noResult : result);
  }

  private void printBookInfoForBooksBefore() {
    System.out.println("Enter year in format dd-MM-yyyy:");
    LocalDate date = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("dd-MM-uuuu"));
    List<String> resultList = bookService.getBookInfoForBooksBefore(date);
    System.out.println(String.join(System.lineSeparator(), resultList));
  }

  private void printBookTitlesForBooksNotReleasedIn(int year) {
    List<String> bookNames = bookService.getBookTitlesForBooksNotReleasedIn(year);
    System.out.println(String.join(System.lineSeparator(), bookNames));
  }

  private void printBookTitlesAndPriceForBookWithPriceNotBetween(int min, int max) {
    List<String> resultList = bookService.getBookTitlesAndPriceForBookWithPriceNotBetween(min, max);
    System.out.println(String.join(System.lineSeparator(), resultList));
  }

  private void printBookTitlesWithEditionAndCopiesLessThan(EditionType editionType, int maxCopies) {
    List<String> bookNames = bookService.getBookTitlesWithEditionAndCopiesLessThan(editionType, maxCopies);
    System.out.println(String.join(System.lineSeparator(), bookNames));
  }

  private void printBookTitlesByAgeRestriction() {
    System.out.println("Enter age restriction to search for:");
    AgeRestriction ageRestriction = AgeRestriction.valueOf(scanner.nextLine().toUpperCase());
    List<String> bookNames = bookService.getAllBooksWithAgeRestriction(ageRestriction);
    System.out.println(String.join(System.lineSeparator(), bookNames));
  }

  private void printALlBooksByAuthorNameOrderByReleaseDate(String firstName, String lastName) {
    bookService
      .findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(firstName, lastName)
      .forEach(System.out::println);
  }

  private void printAllAuthorsAndNumberOfTheirBooks() {
    authorService
      .getAllAuthorsOrderByCountOfTheirBooks()
      .forEach(System.out::println);
  }

  private void printAllAuthorsNamesWithBooksWithReleaseDateBeforeYear(int year) {
    bookService
      .findAllAuthorsWithBooksWithReleaseDateBeforeYear(year)
      .forEach(System.out::println);
  }

  private void printAllBooksAfterYear(int year) {
    bookService
      .findAllBooksAfterYear(year)
      .stream()
      .map(Book::getTitle)
      .forEach(System.out::println);
  }

  private void seedData() throws IOException {
    categoryService.seedCategories();
    authorService.seedAuthors();
    bookService.seedBooks();
  }
}
