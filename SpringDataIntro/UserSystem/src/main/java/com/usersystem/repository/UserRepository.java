package com.usersystem.repository;

import java.time.LocalDateTime;
import java.util.List;

import com.usersystem.model.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

  List<User> findAllByEmailContains(String pattern);
  List<User> findAllByLastTimeLoggedInBefore(LocalDateTime dateTime);
  void deleteAllByIsDeletedIsTrue();
}
