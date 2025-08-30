package com.example.tennisscoreboard.dao;

import com.example.tennisscoreboard.model.entity.Match;

import javax.persistence.EntityManager;

public class MatchRepository extends BaseRepository<Long, Match> {
    public MatchRepository(EntityManager entityManager, Class<Match> clazz) {
        super(entityManager, clazz);
    }
}
