package com.example.tennisscoreboard.servlets;

import com.example.tennisscoreboard.dao.PlayerRepository;
import com.example.tennisscoreboard.model.entity.Player;
import com.example.tennisscoreboard.model.game.CurrentMatch;
import com.example.tennisscoreboard.service.OngoingMatchService;
import com.example.tennisscoreboard.service.PlayerService;
import com.example.tennisscoreboard.util.HibernateUtil;
import lombok.Cleanup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/index.html");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var sessionFactory = HibernateUtil.getSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        PlayerRepository playerRepository = new PlayerRepository(session, Player.class);
        PlayerService playerService = new PlayerService(playerRepository);
        OngoingMatchService ongoingMatchService = OngoingMatchService.getInstance();

        String firstPlayerName = req.getParameter("firstPlayerName");
        String secondPlayerName = req.getParameter("secondPlayerName");

        session.beginTransaction();

        Player firstPlayer = playerService.createPlayer(new Player(firstPlayerName));
        Player secondPlayer = playerService.createPlayer(new Player(secondPlayerName));

        session.flush();

        session.getTransaction().commit();
        UUID uuid = ongoingMatchService.createMatch(firstPlayer, secondPlayer);
        resp.sendRedirect("/match-score?uuid=%s".formatted(uuid));
    }
}
