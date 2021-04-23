package com.han.sega.service;

import com.han.sega.entity.GameTransaction;

import java.util.List;

public interface ApiService {
    int save(GameTransaction gameTransaction);

    List<GameTransaction> findAll();

}
