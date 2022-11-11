package com.usersystem.service;

import com.usersystem.model.entity.Town;

public interface TownService {
    void save(Town town);

    long count();

    Town getRandomTown();
}
