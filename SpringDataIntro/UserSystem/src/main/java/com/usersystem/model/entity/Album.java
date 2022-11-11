package com.usersystem.model.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter

@AllArgsConstructor
@Builder
public class Album extends BaseEntity {

  @Column
  private String name;

  @Column
  private String backgroundColor;

  @Column
  private boolean isPublic;

  @ManyToMany(targetEntity = Picture.class, mappedBy = "albums")
  private Set<Picture> pictures;

  public Album() {
    super();
    pictures = new HashSet<>();
  }
}
