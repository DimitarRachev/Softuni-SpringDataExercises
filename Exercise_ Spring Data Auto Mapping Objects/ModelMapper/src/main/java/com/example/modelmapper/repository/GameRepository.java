package com.example.modelmapper.repository;


import java.util.Optional;

import com.example.modelmapper.model.entity.Game;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
  boolean existsByTitle(String title);

  Optional<Game> findByTitle(String gameName);
}
