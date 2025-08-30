package com.example.tennisscoreboard.model.game;

import com.example.tennisscoreboard.model.entity.Player;
import lombok.*;

@Data
@RequiredArgsConstructor()
public class CurrentMatch {
    private final Player firstPlayer;
    private final Player secondPlayer;
    private int firstPlayerScore = 0;
    private int secondPlayerScore = 0;

    public boolean isEnded() {
        if(firstPlayerScore == 6){
            winner = firstPlayer;
            return true;
        }
        if(secondPlayerScore == 6){
            winner = secondPlayer;
            return true;
        }
        return false;
    }

    private Player winner;
}
