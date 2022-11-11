package com.example.modelmapper.service;

import com.example.modelmapper.model.dto.GameDTO;
import com.example.modelmapper.model.dto.GameRequest;
import com.example.modelmapper.model.dto.GameShortDetails;
import com.example.modelmapper.model.dto.GameShortInfo;
import com.example.modelmapper.model.entity.Game;

import java.util.List;

public interface GameService {

  String deleteGame(long parseLong);

  void addGame(GameRequest gameRequest);

  GameDTO getById(Long id);

  void save(GameDTO gameDTO);

  List<GameShortInfo> getAllGames();


  GameShortDetails getDetailsFor(String gameName);

  Game getByTitle(String title);
}
