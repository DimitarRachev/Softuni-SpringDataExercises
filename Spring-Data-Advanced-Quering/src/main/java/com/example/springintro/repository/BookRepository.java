package com.example.springintro.repository;

import com.example.springintro.model.entity.AgeRestriction;
import com.example.springintro.model.entity.Book;
import com.example.springintro.model.entity.EditionType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.transaction.Transactional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

  List<Book> findAllByReleaseDateAfter(LocalDate releaseDateAfter);

  List<Book> findAllByReleaseDateBefore(LocalDate releaseDateBefore);

  List<Book> findAllByAuthor_FirstNameAndAuthor_LastNameOrderByReleaseDateDescTitle(String author_firstName,
    String author_lastName);

  List<Book> findAllByAgeRestriction(AgeRestriction ageRestriction);

  List<Book> findAllByEditionTypeAndCopiesIsLessThan(EditionType editType, int copyIsLessThan);

  List<Book> findAllByPriceIsLessThanOrPriceGreaterThan(BigDecimal min, BigDecimal max);

  @Query(value = "SELECT B.title FROM Book B WHERE YEAR(B.releaseDate) <> :year")
  List<String> findAllByReleaseDateNotEquals(int year);

  List<Book> findAllByTitleIsContainingIgnoreCase(String search);

  @Query(value = "SELECT B FROM Book B WHERE LOWER(SUBSTRING(B.author.firstName, 1, :length)) = LOWER(:start)")
  List<Book> findAllByAuthorFirstNameStartWith(int length, String start);

  @Query(value = "SELECT COUNT(B) FROM Book B WHERE LENGTH(B.title) > :size ")
  Integer countBookByTitleLonger(int size);

  @Query(value = "SELECT B.title, B.editionType, B.ageRestriction, B.price FROM Book B WHERE B.title = :title")
  String getBookInfoByTitle(String title);

  @Transactional
  Integer deleteAllByCopiesIsLessThan(int copies);
}
