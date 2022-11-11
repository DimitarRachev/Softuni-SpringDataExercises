package com.example.modelmapper.service.impl;


import com.example.modelmapper.model.dto.GameDTO;
import com.example.modelmapper.model.dto.GameRequest;
import com.example.modelmapper.model.dto.GameShortDetails;
import com.example.modelmapper.model.dto.GameShortInfo;
import com.example.modelmapper.model.entity.Game;
import com.example.modelmapper.repository.GameRepository;
import com.example.modelmapper.service.GameService;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GameServiceImpl implements GameService {
  private final GameRepository gameRepository;
  private final ModelMapper modelMapper;

  @Override public String deleteGame(long parseLong) {
    Game game = gameRepository.findById(parseLong)
      .orElseThrow(() -> new IllegalArgumentException("Invalid ID!"));
    gameRepository.delete(game);
    return "Deleted " + game.getTitle();
  }

  @Override public GameDTO getById(Long id) {
    return modelMapper.map(gameRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Invalid game id!")),
      GameDTO.class);
  }

  @Override public void save(GameDTO gameDTO) {
    Game game = modelMapper.map(gameDTO, Game.class);
    gameRepository.save(game);
  }

  @Override public List<GameShortInfo> getAllGames() {
    return gameRepository.findAll()
      .stream()
      .map(g -> modelMapper.map(g, GameShortInfo.class))
      .toList();
  }

  @Override public GameShortDetails getDetailsFor(String gameName) {
    return modelMapper.map(
      gameRepository.findByTitle(gameName)
        .orElseThrow(() -> new IllegalArgumentException("Game with title " + gameName + " doesen't exists")),
      GameShortDetails.class
    );
  }

  @Override public Game getByTitle(String title) {
    return gameRepository
      .findByTitle(title)
      .orElseThrow(() -> new IllegalArgumentException("Game " + title + " not found"));
  }

  @Override public void addGame(GameRequest gameRequest) {
    if (gameRepository.existsByTitle(gameRequest.getTitle())) {
      throw new IllegalArgumentException("Game already exists");
    }
    Game game = modelMapper.map(gameRequest, Game.class);
    gameRepository.save(game);
  }
}
