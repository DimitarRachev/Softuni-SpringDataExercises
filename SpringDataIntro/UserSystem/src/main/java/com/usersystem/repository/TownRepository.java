package com.usersystem.repository;

import com.usersystem.model.entity.Town;
import com.usersystem.model.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TownRepository extends JpaRepository<Town, User> {
  Optional<Town> getByName(String userPart);
  Town findByIdIs(long id);
}
