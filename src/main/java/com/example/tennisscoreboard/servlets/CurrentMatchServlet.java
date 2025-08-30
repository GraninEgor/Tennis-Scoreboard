package com.example.tennisscoreboard.servlets;

import com.example.tennisscoreboard.dao.MatchRepository;
import com.example.tennisscoreboard.dao.PlayerRepository;
import com.example.tennisscoreboard.mapper.GameAndEntityMapper;
import com.example.tennisscoreboard.model.entity.Match;
import com.example.tennisscoreboard.model.entity.Player;
import com.example.tennisscoreboard.model.game.CurrentMatch;
import com.example.tennisscoreboard.service.EndedMatchService;
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
import java.util.Optional;
import java.util.UUID;

@WebServlet("/match-score")
public class CurrentMatchServlet extends HttpServlet {

    OngoingMatchService ongoingMatchService = OngoingMatchService.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        Optional<CurrentMatch> currentMatch = ongoingMatchService.getMatches().get(uuid);
        currentMatch.ifPresentOrElse(map -> {
            req.setAttribute("firstPlayerName", map.getFirstPlayer().getName());
            req.setAttribute("secondPlayerName", map.getSecondPlayer().getName());
            req.setAttribute("firstPlayerScore", map.getFirstPlayerScore());
            req.setAttribute("secondPlayerScore", map.getSecondPlayerScore());
        }, () -> {
            try {
                resp.sendRedirect("404");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        req.getRequestDispatcher("/WEB-INF/jsp/match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UUID uuid = UUID.fromString(req.getParameter("uuid"));
        Optional<CurrentMatch> currentMatch = ongoingMatchService.getMatches().get(uuid);
        if(currentMatch.isEnded()){
            @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
            @Cleanup var session = sessionFactory.openSession();
            MatchRepository matchRepository = new MatchRepository(session, Match.class);
            EndedMatchService endedMatchService = new EndedMatchService(matchRepository);
            PlayerRepository playerRepository = new PlayerRepository(session, Player.class);
            PlayerService playerService = new PlayerService(playerRepository);
            GameAndEntityMapper gameAndEntityMapper = new GameAndEntityMapper(playerService);
            endedMatchService.create(gameAndEntityMapper.toEntity(currentMatch));
            req.setAttribute("firstPlayerName",currentMatch.getFirstPlayer().getName());
            req.setAttribute("secondPlayerName",currentMatch.getSecondPlayer().getName());
            req.setAttribute("firstPlayerScore",currentMatch.getFirstPlayerScore());
            req.setAttribute("secondPlayerScore",currentMatch.getSecondPlayerScore());
            req.setAttribute("winnerName", currentMatch.getWinner().getName());
            ongoingMatchService.getMatches().remove(uuid);
            req.getRequestDispatcher("/WEB-INF/jsp/ended-match.jsp").forward(req, resp);
        }
        else{
            if(req.getParameter("action").equals("firstPlayerGotPoint")){
                currentMatch.setFirstPlayerScore(currentMatch.getFirstPlayerScore() + 1);
            }
            else if(req.getParameter("action").equals("secondPlayerGotPoint")){
                currentMatch.setSecondPlayerScore(currentMatch.getSecondPlayerScore() + 1);
            }
            req.setAttribute("firstPlayerName",currentMatch.getFirstPlayer().getName());
            req.setAttribute("secondPlayerName",currentMatch.getSecondPlayer().getName());
            req.setAttribute("firstPlayerScore",currentMatch.getFirstPlayerScore());
            req.setAttribute("secondPlayerScore",currentMatch.getSecondPlayerScore());
            req.getRequestDispatcher("/WEB-INF/jsp/match-score.jsp").forward(req, resp);
        }
    }
}
