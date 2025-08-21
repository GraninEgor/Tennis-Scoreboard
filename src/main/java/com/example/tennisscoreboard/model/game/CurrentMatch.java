package com.example.tennisscoreboard.model.game;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CurrentMatch {
    private Long firstPlayerId;
    private Long secondPlayerId;
    private int score;
}
