package com.example.tennisscoreboard.util;

import com.example.tennisscoreboard.dao.MatchRepository;
import com.example.tennisscoreboard.dao.PlayerRepository;
import com.example.tennisscoreboard.mapper.GameAndEntityMapper;
import com.example.tennisscoreboard.model.entity.Match;
import com.example.tennisscoreboard.model.entity.Player;
import com.example.tennisscoreboard.service.EndedMatchService;
import com.example.tennisscoreboard.service.PlayerService;
import lombok.Cleanup;
import org.h2.engine.Session;
import org.hibernate.SessionFactory;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;


@WebListener
public class DatabaseInitializer implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        var sessionFactory = HibernateUtil.getSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        MatchRepository matchRepository = new MatchRepository(session, Match.class);
        EndedMatchService endedMatchService = new EndedMatchService(matchRepository);
        PlayerRepository playerRepository = new PlayerRepository(session, Player.class);
        PlayerService playerService = new PlayerService(playerRepository);

        session.beginTransaction();

        Player player1 = playerService.createPlayer(new Player("Peyta"));
        Player player2 = playerService.createPlayer(new Player("Eugenuy"));
        Player player3 = playerService.createPlayer(new Player("Misha"));
        Player player4 = playerService.createPlayer(new Player("Vasya"));

        endedMatchService.create(Match.builder()
                .player1(player1)
                .player2(player2)
                .winner(player1)
                .build());
        endedMatchService.create(Match.builder()
                .player1(player1)
                .player2(player2)
                .winner(player1)
                .build());
        endedMatchService.create(Match.builder()
                .player1(player1)
                .player2(player2)
                .winner(player1)
                .build());
        endedMatchService.create(Match.builder()
                .player1(player1)
                .player2(player2)
                .winner(player1)
                .build());
        endedMatchService.create(Match.builder()
                .player1(player1)
                .player2(player2)
                .winner(player1)
                .build());
        endedMatchService.create(Match.builder()
                .player1(player1)
                .player2(player2)
                .winner(player1)
                .build());
        endedMatchService.create(Match.builder()
                .player1(player1)
                .player2(player2)
                .winner(player1)
                .build());
        endedMatchService.create(Match.builder()
                .player1(player1)
                .player2(player2)
                .winner(player1)
                .build());
        endedMatchService.create(Match.builder()
                .player1(player1)
                .player2(player2)
                .winner(player1)
                .build());
        endedMatchService.create(Match.builder()
                .player1(player1)
                .player2(player2)
                .winner(player1)
                .build());
        endedMatchService.create(Match.builder()
                .player1(player1)
                .player2(player2)
                .winner(player1)
                .build());
        endedMatchService.create(Match.builder()
                .player1(player1)
                .player2(player2)
                .winner(player1)
                .build());
        endedMatchService.create(Match.builder()
                .player1(player1)
                .player2(player2)
                .winner(player1)
                .build());
        endedMatchService.create(Match.builder()
                .player1(player1)
                .player2(player2)
                .winner(player1)
                .build());
        endedMatchService.create(Match.builder()
                .player1(player1)
                .player2(player2)
                .winner(player1)
                .build());
        endedMatchService.create(Match.builder()
                .player1(player1)
                .player2(player2)
                .winner(player1)
                .build());
        endedMatchService.create(Match.builder()
                .player1(player1)
                .player2(player2)
                .winner(player1)
                .build());
        endedMatchService.create(Match.builder()
                .player1(player1)
                .player2(player2)
                .winner(player1)
                .build());
        endedMatchService.create(Match.builder()
                .player1(player1)
                .player2(player2)
                .winner(player1)
                .build());
        endedMatchService.create(Match.builder()
                .player1(player1)
                .player2(player2)
                .winner(player1)
                .build());
        endedMatchService.create(Match.builder()
                .player1(player1)
                .player2(player2)
                .winner(player1)
                .build());
        endedMatchService.create(Match.builder()
                .player1(player1)
                .player2(player2)
                .winner(player1)
                .build());
        endedMatchService.create(Match.builder()
                .player1(player1)
                .player2(player2)
                .winner(player1)
                .build());
        endedMatchService.create(Match.builder()
                .player1(player1)
                .player2(player2)
                .winner(player1)
                .build());
        endedMatchService.create(Match.builder()
                .player1(player1)
                .player2(player2)
                .winner(player1)
                .build());
        endedMatchService.create(Match.builder()
                .player1(player1)
                .player2(player2)
                .winner(player1)
                .build());
        endedMatchService.create(Match.builder()
                .player1(player4)
                .player2(player3)
                .winner(player3)
                .build());
        session.getTransaction().commit();
    }
}
