package com.example.tennisscoreboard.dao;

import com.example.tennisscoreboard.model.entity.Player;

import javax.persistence.EntityManager;

public class PlayerRepository extends BaseRepository<Long, Player> {

    public PlayerRepository(EntityManager entityManager, Class<Player> clazz) {
        super(entityManager, clazz);
    }
}
