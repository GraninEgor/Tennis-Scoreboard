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
import java.util.Collections;
import java.util.List;

@WebServlet("/matches")
public class EndedMatchesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String pageParam = req.getParameter("page");
        String name = req.getParameter("name");

        int page;

        if(pageParam == null){
            page =  0;
        }
        else{
            page = Integer.parseInt(pageParam) - 1;
        }

        var sessionFactory = HibernateUtil.getSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        MatchRepository matchRepository = new MatchRepository(session, Match.class);
        EndedMatchService endedMatchService = new EndedMatchService(matchRepository);

        List<Match> matches = Collections.emptyList();
        long amount;

        if(name == null || name.isEmpty()){
            matches = endedMatchService.findAll(page);
            amount = endedMatchService.findAmountOfMatches();
        }
        else{
            matches = endedMatchService.findAll(page, name);
            amount = endedMatchService.findAmountOfMatches(name);
        }

        long pages = (long) Math.ceil((double) amount / 10);

        req.setAttribute("name", name);
        req.setAttribute("pages", pages);
        req.setAttribute("matches", matches);

        req.getRequestDispatcher("/WEB-INF/jsp/ended-matches.jsp").forward(req,resp);
    }
}
