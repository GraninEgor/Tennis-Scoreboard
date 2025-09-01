package com.example.tennisscoreboard.dao;

import com.example.tennisscoreboard.model.entity.Match;
import com.querydsl.jpa.impl.JPAQuery;
import javax.persistence.EntityManager;
import java.util.List;

import static com.example.tennisscoreboard.model.entity.QMatch.match;

public class MatchRepository extends BaseRepository<Long, Match> {
    public MatchRepository(EntityManager entityManager, Class<Match> clazz) {
        super(entityManager, clazz);
    }

    public List<Match> findAll(int page, String name) {
        return new JPAQuery<Match>(entityManager).select(match).from(match)
                .where(match.player1.name.eq(name).or(match.player2.name.eq(name)))
                .limit(10)
                .offset(page * 10)
                .fetch();
    }

}
