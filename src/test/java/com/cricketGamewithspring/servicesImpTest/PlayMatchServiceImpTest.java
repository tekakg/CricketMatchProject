package com.cricketGamewithspring.servicesImpTest;

import com.cricketGamewithspring.cricketGame.model.Match;
import com.cricketGamewithspring.cricketGame.model.Player;
import com.cricketGamewithspring.cricketGame.model.Team;

import com.cricketGamewithspring.cricketGame.servicesImp.InningServiceImp;
import com.cricketGamewithspring.cricketGame.servicesImp.PlayMatchServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = PlayMatchServiceImp.class)
@ExtendWith(MockitoExtension.class)
public class PlayMatchServiceImpTest {
    @InjectMocks
    private PlayMatchServiceImp playMatchService;
    @MockBean
    private InningServiceImp inningServiceImp;


    @Test
    void PlayMatch() {
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
        match.setTotalOvers(20);
        List<Team> listTeam = new ArrayList<>();
        listTeam.add(team1);
        listTeam.add(team2);

        String Output = playMatchService.playMatch(team1, team2, match, "Team 1");
        assertNotNull(Output);

    }
}
