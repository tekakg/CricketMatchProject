package com.cricketGamewithspring.cricketGame.services;

import com.cricketGamewithspring.cricketGame.model.Team;

public interface TossService {

    String getToss(Team team1, Team team2);
}
