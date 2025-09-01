package com.example.tennisscoreboard.dao;

import com.example.tennisscoreboard.model.entity.Match;
import com.example.tennisscoreboard.model.entity.Player;
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

    public List<Match> findAll(int page) {
        return new JPAQuery<Match>(entityManager).select(match).from(match)
                .limit(10)
                .offset(page * 10)
                .fetch();
    }

    public Long findAmountOfMatches() {
        Long amount = entityManager.createQuery(
                "SELECT COUNT(*) FROM Match", Long.class).getSingleResult();
        return amount;
    }

    public Long findAmountOfMatches(String name) {
        Long amount = entityManager.createQuery(
                "SELECT COUNT(m) FROM Match m WHERE m.player1.name = :name OR m.player2.name = :name", Long.class)
                .setParameter("name", name.trim())
                .getSingleResult();
        return amount;
    }

}
