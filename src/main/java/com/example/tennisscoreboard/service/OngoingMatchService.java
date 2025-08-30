package com.example.tennisscoreboard.service;

import com.example.tennisscoreboard.model.entity.Player;
import com.example.tennisscoreboard.model.game.CurrentMatch;
import lombok.Getter;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchService {

    private static final OngoingMatchService INSTANCE = new OngoingMatchService();

    public static OngoingMatchService getInstance(){
        return INSTANCE;
    }

    @Getter
    private final Map<UUID, Optional<CurrentMatch>> matches = new ConcurrentHashMap<>();

    public UUID createMatch(Player firstPlayer, Player secondPlayer){
        Optional<CurrentMatch> newCurrentMatch = Optional.of(new CurrentMatch(firstPlayer,secondPlayer));
        UUID uuid = UUID.randomUUID();
        matches.put(uuid, newCurrentMatch);
        return uuid;
    }

    private OngoingMatchService(){}
}
