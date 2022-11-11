package com.example.springintro.model.entity;

import javax.persistence.*;

import java.util.Set;

@Entity
@Table(name = "authors")
@NamedStoredProcedureQueries({
  @NamedStoredProcedureQuery(name = "new_procedure", procedureName = "new_procedure", parameters = {
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "firstName", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.IN, name = "lastName", type = String.class),
    @StoredProcedureParameter(mode = ParameterMode.OUT, name = "books_count", type = Integer.class),
  })})
public class Author extends BaseEntity {

  private String firstName;
  private String lastName;
  private Set<Book> books;

  public Author() {
  }

  public Author(String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  @Column(name = "first_name")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Column(name = "last_name", nullable = false)
  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
  public Set<Book> getBooks() {
    return books;
  }

  public void setBooks(Set<Book> books) {
    this.books = books;
  }
}
