package com.usersystem.model.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity
@Data
@AllArgsConstructor
@Builder
public class User extends BaseEntity {
    @Transient
    private static final String PASSWORD_REGEX =
            "^(?=.*[A-Za-z\\d!@#$%^&*()_+<>?])(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])(?=.*[!@#$%^&*()_+<>?])[A-Za-z\\d][A-Za-z\\d!@#$%^&*()_+<>?]{4,49}$";

    @Transient
    private static final String EMAIL_REGEX = "^[A-Za-z0-9]+([-_.]?[A-Za-z0-9]+)*@([A-Za-z]+(-[A-Za-z]+)*)([.]([A-Za-z]+(-[A-Za-z]+)*))+$";

    @Size(min = 4, max = 30)
    @Column(nullable = false, unique = true)
    private String username;

    @Size(min = 6, max = 50)
    @Column(nullable = false)
    @Pattern(regexp = PASSWORD_REGEX)
    private String password;

    @Column(nullable = false, unique = true)
    @Email(regexp = EMAIL_REGEX)
    private String email;

    @Column(name = "registered_on")
    private LocalDateTime registeredOn;

    @Column(name = "last_time_logged_in ")
    private LocalDateTime lastTimeLoggedIn;

    @Size(min = 1, max = 120)
    @Column
    private int age;

    @Column(name = "is_deleted")
    private boolean isDeleted;

    @ManyToOne(targetEntity = Town.class)
    private Town bornTown;

    @ManyToOne(targetEntity = Town.class)
    private Town currentTown;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Transient
    private String fullName;

    @ManyToMany
    private Set<User> friends;

    @OneToMany
    private Set<Album> albums;

    public User() {
        friends = new HashSet<>();
        albums = new HashSet<>();
    }

    public User(String firstName, String lastName, String username, String password, String email, int age, Town bornTown,
                Town livingTown, LocalDate lastTimeLongedIn) {
        this();
    }

    public String getFullName(String fullName) {
        return firstName + " " + lastName;

    }
}
