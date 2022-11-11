package com.usersystem.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Entity
@AllArgsConstructor
@Builder
public class Picture extends BaseEntity{
  @Column
  private String title;

  @Column
  private String caption;

  @Column
  private String path;

  @ManyToMany(targetEntity = Album.class)
  private Set<Album> albums;

  @ManyToOne
  private User owner;

  public Picture() {
    albums = new HashSet<>();
  }
}
