package com.booksystem.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.booksystem.model.entity.Author;
import com.booksystem.repository.AuthorRepository;
import com.booksystem.service.AuthorService;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;
    private final Random random;

    @Override
    public long count() {
        return authorRepository.count();
    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author getRandomAuthor() {
        long size = authorRepository.count();
        long authorId = random.nextLong((int) size) + 1;
        return this.authorRepository.findById(authorId).orElseThrow(RuntimeException::new);
    }

    @Override
    public List<String> findAuthorNamesPublishedBefore(int year) {

        return authorRepository.findDistinctByBooksReleaseDateBefore(LocalDate.of(year, 1, 1))
                .stream()
                .map(a -> a.getFirstName() + " " + a.getLastName())
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<String> findAuthorsNameAndBookCountOrderedByBookCount() {
      return  authorRepository
              .findAll()
              .stream()
              .sorted((a1, a2) -> Integer.compare(a2.getBooks().size(), a1.getBooks().size()))
              .map(a -> a.getFirstName() + " " + a.getLastName() + " " + a.getBooks().size())
              .collect(Collectors.toList());
    }
}
