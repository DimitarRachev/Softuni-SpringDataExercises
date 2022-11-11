package com.booksystem.repository;

import com.booksystem.model.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import com.booksystem.model.entity.Author;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findDistinctByBooksReleaseDateBefore(LocalDate Date);
    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);
}
