package com.example.tennisscoreboard.service;

import com.example.tennisscoreboard.model.game.CurrentMatch;
import lombok.Getter;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class OngoingMatchService {

    private static final OngoingMatchService INSTANCE = new OngoingMatchService();

    public static OngoingMatchService getInstance(){
        return INSTANCE;
    }

    private final Map<UUID, CurrentMatch> matches = new ConcurrentHashMap<>();

    public UUID createMatch(Long firstPlayerId, Long secondPlayerId){
        CurrentMatch newCurrentMatch = new CurrentMatch(firstPlayerId,secondPlayerId, 0);
        UUID uuid = UUID.randomUUID();
        matches.put(uuid, newCurrentMatch);
        return uuid;
    }

    private OngoingMatchService(){}
}
