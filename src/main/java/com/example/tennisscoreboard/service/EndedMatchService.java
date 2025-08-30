package com.example.tennisscoreboard.service;

import com.example.tennisscoreboard.dao.MatchRepository;
import com.example.tennisscoreboard.model.entity.Match;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EndedMatchService {
    private final MatchRepository matchRepository;

    public void create(Match match){
        matchRepository.save(match);
    }
}
