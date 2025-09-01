import com.example.tennisscoreboard.dao.MatchRepository;
import com.example.tennisscoreboard.dao.PlayerRepository;
import com.example.tennisscoreboard.model.entity.Match;
import com.example.tennisscoreboard.model.entity.Player;
import com.example.tennisscoreboard.service.EndedMatchService;
import com.example.tennisscoreboard.service.PlayerService;
import com.example.tennisscoreboard.util.HibernateUtil;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

public class HibernateTest {
    void saveTest(){
        var sessionFactory = HibernateUtil.getSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();
        MatchRepository matchRepository = new MatchRepository(session, Match.class);
        EndedMatchService endedMatchService = new EndedMatchService(matchRepository);
        PlayerRepository playerRepository = new PlayerRepository(session,Player.class);
        PlayerService playerService = new PlayerService(playerRepository);

        Player player1 = playerService.createPlayer(new Player("Peyta"));
        Player player2 = playerService.createPlayer(new Player("Vasya"));

        endedMatchService.create(Match.builder()
                        .player1(player1)
                        .player2(player2)
                        .winner(player2)
                        .build());


        session.getTransaction().commit();
        System.out.println(endedMatchService.findAll());

        var sessionFactory2 = HibernateUtil.getSessionFactory();
        @Cleanup var session2 = sessionFactory2.openSession();

        MatchRepository matchRepository2 = new MatchRepository(session2, Match.class);
        EndedMatchService endedMatchService2 = new EndedMatchService(matchRepository2);
        PlayerRepository playerRepository2 = new PlayerRepository(session2,Player.class);
        PlayerService playerService2 = new PlayerService(playerRepository2);

        session2.beginTransaction();

        Player player4 = playerService2.createPlayer(new Player("Peyta"));
        Player player5 = playerService2.createPlayer(new Player("Eugenuy"));

        endedMatchService2.create(Match.builder()
                .player1(player4)
                .player2(player5)
                .winner(player5)
                .build());

        session2.getTransaction().commit();

        System.out.println(endedMatchService2.findAll());
    }

    @Test
    void amoutTest(){
        var sessionFactory = HibernateUtil.getSessionFactory();
        @Cleanup var session5 = sessionFactory.openSession();

        saveTest();

        session5.beginTransaction();
        MatchRepository matchRepository3 = new MatchRepository(session5, Match.class);
        EndedMatchService endedMatchService3 = new EndedMatchService(matchRepository3);
        PlayerRepository playerRepository3= new PlayerRepository(session5,Player.class);
        PlayerService playerService3 = new PlayerService(playerRepository3);

        System.out.println(endedMatchService3.findAmountOfMatches());

        session5.getTransaction().commit();
    }
}
