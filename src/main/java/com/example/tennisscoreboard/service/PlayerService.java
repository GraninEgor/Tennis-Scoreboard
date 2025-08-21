package com.example.tennisscoreboard.service;

import com.example.tennisscoreboard.dao.PlayerRepository;
import com.example.tennisscoreboard.model.entity.Player;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    public void createPlayer(Player player){
        playerRepository.save(player);
    }
}
