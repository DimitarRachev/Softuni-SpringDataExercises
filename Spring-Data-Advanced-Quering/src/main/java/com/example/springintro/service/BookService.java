package com.example.springintro.service;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
  void seedBooks() throws IOException;

  List<Book> findAllBooksAfterYear(int year);

  List<String> findAllAuthorsWithBooksWithReleaseDateBeforeYear(int year);

  List<String> findAllBooksByAuthorFirstAndLastNameOrderByReleaseDate(String firstName, String lastName);

  List<String> getAllBooksWithAgeRestriction(AgeRestriction ageRestriction);

  List<String> getBookTitlesWithEditionAndCopiesLessThan(EditionType editionType, int maxCopies);

  List<String> getBookTitlesAndPriceForBookWithPriceNotBetween(int min, int max);

  List<String> getBookTitlesForBooksNotReleasedIn(int year);

  List<String> getBookInfoForBooksBefore(LocalDate date);

  List<String> getBookTitlesContaining(String search);

  List<String> getBookTitlesWithAuthorFirstNameStartsWith(String firstNameStart);

  int getCountBookWithTitleLongerThan(int length);

  String getBookInfoByTitle(String title);

  String increaseBookCopies(LocalDate date, int amount);

  int removeBooksWithCopiesLessThan(int copies);

  String getTotalBooks(String firstName, String lastName);
}
