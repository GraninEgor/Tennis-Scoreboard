package com.example.tennisscoreboard.service;

import com.example.tennisscoreboard.dao.MatchRepository;
import com.example.tennisscoreboard.model.entity.Match;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class EndedMatchService {
    private final MatchRepository matchRepository;

    public void create(Match match){
        matchRepository.save(match);
    }

    public List<Match> findAll(int page, String name){
        return matchRepository.findAll(page, name);
    }


    public List<Match> findAll(){
        return matchRepository.findAll();
    }
}
