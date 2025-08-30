import com.example.tennisscoreboard.dao.MatchRepository;
import com.example.tennisscoreboard.dao.PlayerRepository;
import com.example.tennisscoreboard.model.entity.Match;
import com.example.tennisscoreboard.model.entity.Player;
import com.example.tennisscoreboard.service.EndedMatchService;
import com.example.tennisscoreboard.util.HibernateUtil;
import lombok.Cleanup;
import org.junit.jupiter.api.Test;

public class HibernateTest {
    @Test
    void saveTest(){
        @Cleanup var sessionFactory = HibernateUtil.buildSessionFactory();
        @Cleanup var session = sessionFactory.openSession();

        session.beginTransaction();
        MatchRepository matchRepository = new MatchRepository(session, Match.class);
        EndedMatchService endedMatchService = new EndedMatchService(matchRepository);
        PlayerRepository playerRepository = new PlayerRepository(session,Player.class);

        Player player1 = new Player("Vasya");
        Player player2 = new Player("Petya");

        playerRepository.save(player1);
        playerRepository.save(player2);

        endedMatchService.create(Match.builder()
                        .player1(player1)
                        .player2(player2)
                        .winner(player2)
                        .build());
        session.getTransaction().commit();
        System.out.println(endedMatchService.findAll());
    }
}
