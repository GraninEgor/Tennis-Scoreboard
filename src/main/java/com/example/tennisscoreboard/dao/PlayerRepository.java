package com.example.tennisscoreboard.dao;

import com.example.tennisscoreboard.model.entity.Player;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class PlayerRepository extends BaseRepository<Long, Player> {

    public PlayerRepository(EntityManager entityManager, Class<Player> clazz) {
        super(entityManager, clazz);
    }

    @Override
    public Player save(Player entity) {
        if (entity.getId() != null) {
            return entityManager.merge(entity);
        }

        Optional<Player> existing = findByName(entity.getName());
        if (existing.isPresent()) {
            return entityManager.getReference(Player.class, existing.get().getId());
        }

        entityManager.persist(entity);
        return entity;
    }

    public Optional<Player> findByName(String name) {
        List<Player> result = entityManager.createQuery(
                        "SELECT p FROM Player p WHERE p.name = :name", Player.class)
                .setParameter("name", name)
                .getResultList();
        return result.stream().findFirst();
    }
}
