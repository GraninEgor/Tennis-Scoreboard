package com.example.tennisscoreboard;

import com.example.tennisscoreboard.model.entity.Player;
import com.example.tennisscoreboard.util.HibernateUtil;
import lombok.Cleanup;

public class HibernateRunner {
    public static void main(String[] args) {
        @Cleanup var sessionFactory = HibernateUtil.getSessionFactory();
        @Cleanup var session = sessionFactory.openSession();
        session.beginTransaction();

        Player player = new Player("Dima");

        session.save(player);

        System.out.println(session.get(Player.class, 1L));

        session.getTransaction().commit();
    }
}
