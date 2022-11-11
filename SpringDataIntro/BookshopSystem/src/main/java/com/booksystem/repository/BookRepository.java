package com.booksystem.repository;

import com.booksystem.model.entity.Author;
import com.booksystem.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByReleaseDateAfter(LocalDate Date);
    List<Book> findAllByAuthorOrderByReleaseDateDescTitleAsc(Author author);
}
