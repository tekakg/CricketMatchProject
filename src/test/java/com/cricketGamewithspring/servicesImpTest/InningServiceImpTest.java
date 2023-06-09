package com.cricketGamewithspring.servicesImpTest;

import com.cricketGamewithspring.cricketGame.model.Ball;
import com.cricketGamewithspring.cricketGame.model.Match;
import com.cricketGamewithspring.cricketGame.model.Player;
import com.cricketGamewithspring.cricketGame.model.Team;
import com.cricketGamewithspring.cricketGame.servicesImp.InningServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest(classes = InningServiceImp.class)
@ExtendWith(MockitoExtension.class)
public class InningServiceImpTest {

    @InjectMocks
    private InningServiceImp inningServiceImp;

    @Test
    void matchInnings() {
        // Arrange
        Team team1 = new Team();
        Team team2 = new Team();
        team1.setTeamName("Team1");
        team2.setTeamName("Team2");
        team1.setTotalPlayers(2);
        team2.setTotalPlayers(2);
        List<Player> team1Players = new ArrayList<>();
        List<Player> team2Players = new ArrayList<>();
        Player player1 = new Player(1, "Player 1", "Batsman");
        Player player2 = new Player(2, "Player 2", "Bowler");
        Player player3 = new Player(3, "Player 3", "Batsman");
        Player player4 = new Player(4, "Player 4", "Bowler");
        team1Players.add(player1);
        team1Players.add(player2);
        team2Players.add(player3);
        team2Players.add(player4);
        team1.setListOfPlayers(team1Players);
        team2.setListOfPlayers(team2Players);
        Match match = new Match();
        List<Ball> ballHistory = new ArrayList<>();

        inningServiceImp.matchInnings(team1, team2, match, ballHistory);
        List<Ball>newBallHistory=new ArrayList<>();
        newBallHistory.add(new Ball(0,0));
        Assertions.assertNotNull(team1);
        Assertions.assertNotNull(team2);
        Assertions.assertNotEquals(newBallHistory,ballHistory);
    }
}
