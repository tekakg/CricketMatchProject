package com.cricketGamewithspring.cricketGame.services;

import com.cricketGamewithspring.cricketGame.model.Ball;
import com.cricketGamewithspring.cricketGame.model.Match;
import com.cricketGamewithspring.cricketGame.model.Team;

import java.util.List;

public interface SecondInningService {

    public List<Team> secondInnings(Team team1, Team team2, Match match, List<Ball> ballHistory);
}
