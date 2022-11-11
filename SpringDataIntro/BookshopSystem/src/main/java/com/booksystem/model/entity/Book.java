package com.booksystem.model.entity;

import com.booksystem.model.enums.AgeRestriction;
import com.booksystem.model.enums.EditionType;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book extends BaseEntity{

    @Enumerated(EnumType.ORDINAL)
    private AgeRestriction ageRestriction;

    @Column(nullable = false)
    private int copies;

    @Column(length = 1000)
    private String description;

    @Enumerated
    private EditionType editionType;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(name = "release_date")
    private LocalDate releaseDate;

    @Column(nullable = false, length = 50)
    private String title;

    @ManyToOne
    private Author author;

    @ManyToMany(targetEntity = Category.class)
    private Set<Category> categories;
}
