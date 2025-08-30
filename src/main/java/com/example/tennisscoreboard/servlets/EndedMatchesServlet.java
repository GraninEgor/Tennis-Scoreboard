package com.example.tennisscoreboard.servlets;

import com.example.tennisscoreboard.dao.MatchRepository;
import com.example.tennisscoreboard.model.entity.Match;
import com.example.tennisscoreboard.service.EndedMatchService;
import com.example.tennisscoreboard.util.HibernateUtil;
import lombok.Cleanup;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class EndedMatchesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        var sessionFactory = HibernateUtil.getSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        MatchRepository matchRepository = new MatchRepository(session, Match.class);
        EndedMatchService endedMatchService = new EndedMatchService(matchRepository);

        List<Match> matches = endedMatchService.findAll();

        req.setAttribute("matches", matches);
        req.getRequestDispatcher("/WEB-INF/jsp/ended-matches.jsp").forward(req,resp);
    }
}
