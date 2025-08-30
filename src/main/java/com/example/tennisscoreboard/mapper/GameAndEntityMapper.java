package com.example.tennisscoreboard.mapper;

import com.example.tennisscoreboard.dao.MatchRepository;
import com.example.tennisscoreboard.dao.PlayerRepository;
import com.example.tennisscoreboard.model.entity.Match;
import com.example.tennisscoreboard.model.entity.Player;
import com.example.tennisscoreboard.model.game.CurrentMatch;
import com.example.tennisscoreboard.service.PlayerService;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class GameAndEntityMapper {
    private final PlayerService playerService;

    public Match toEntity(CurrentMatch currentMatch){
        return Match.builder()
                .player1(currentMatch.getFirstPlayer())
                .player2(currentMatch.getSecondPlayer())
                .winner(currentMatch.getWinner())
                .build();
    }
}
