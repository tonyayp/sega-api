package com.han.sega.service.impl;

import com.han.sega.entity.GameTransaction;
import com.han.sega.mapper.ApiMapper;
import com.han.sega.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiServiceImpl implements ApiService {

    @Autowired
    private ApiMapper apiMapper;

    @Override
    public int save(GameTransaction gameTransaction) {

        int count = apiMapper.insert(gameTransaction);

        return count;
    }

    @Override
    public List<GameTransaction> findAll() {

        List<GameTransaction> list = apiMapper.findAll();

        return list;
    }
}
