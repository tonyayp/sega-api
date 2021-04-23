package com.han.sega.mapper;

import com.han.sega.entity.GameTransaction;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ApiMapper {
    int insert(GameTransaction gameTransaction);

    List<GameTransaction> findAll();

}
