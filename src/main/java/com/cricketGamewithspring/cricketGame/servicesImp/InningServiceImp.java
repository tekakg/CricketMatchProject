package com.cricketGamewithspring.cricketGame.servicesImp;

import com.cricketGamewithspring.cricketGame.model.Ball;
import com.cricketGamewithspring.cricketGame.model.Match;
import com.cricketGamewithspring.cricketGame.model.Player;
import com.cricketGamewithspring.cricketGame.model.Team;
import com.cricketGamewithspring.cricketGame.services.InningService;
import com.cricketGamewithspring.cricketGame.services.RunGeneratingService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Data
@Service
@RequiredArgsConstructor

/**
 This method simulates an inning of a cricket match for a given BattingTeam and BowlingTeam.
 It generates runs for each ball based on the RunGeneratingService implementation.
 It keeps track of ball history and player statistics like balls faced, wickets taken and runs scored.
 It also handles the situation of a player getting out, a no-ball or a wide being bowled and a change of bowler.
 The inning ends when all the overs are completed or all the wickets are taken.
*/
public class InningServiceImp implements InningService {
    private static final Integer NO_OF_BALLS_IN_OVER = 6;

    public void matchInnings(Team BattingTeam, Team BowlingTeam, Match match, List<Ball> ballHistory) {

        Player playerNumber1 = BattingTeam.getListOfPlayers().get(0);
        Player playerNumber2 = BattingTeam.getListOfPlayers().get(1);
        Player Striker = playerNumber1;
        Player nonStriker = playerNumber2;
        Player Bowler = BowlingTeam.listOfPlayers.get(0);
        int inningEnd = 0, overnum = 0, ballnum = 0;
        RunGeneratingService runGeneratingService = new RunGeneratingServiceImp();
        for (overnum = 0; overnum < match.getTotalOvers(); overnum++) {
            for (ballnum = 0; ballnum < NO_OF_BALLS_IN_OVER; ballnum++) {
                Bowler.incrementBallsBowled();
                RunType runtype = runGeneratingService.generateRunType();
                int run = runtype.getRun();
                Ball nball = new Ball(overnum, ballnum + 1, Bowler.getName(), Striker.getName(), runtype);
                ballHistory.add(nball);
                if (run == 7) {
                    Striker.incrementBallsFaced();
                    BattingTeam.incrementWicket();
                    Bowler.incrementWickets();
                    if (BattingTeam.getWicket() == (BattingTeam.getTotalPlayers() - 1)) {
                        inningEnd = 1;
                        break;
                    } else {
                        for (Player player : BattingTeam.listOfPlayers) {
                            if (Striker == player) {
                                player.setPout(true);
                                Striker = BattingTeam.getNewBatsman(nonStriker, BattingTeam);
                                break;
                            }
                        }
                    }
                } else if (runtype == RunType.NOBALL || runtype == RunType.WIDE) {
                    BattingTeam.incrementRun(run);
                    Striker.incrementRun(run);
                    ballnum--;
                    if (BattingTeam.getScore() > BowlingTeam.getScore() && BowlingTeam.getScore() > 0) {
                        inningEnd = 1;
                        break;
                    }
                } else {
                    BattingTeam.incrementRun(run);
                    Striker.incrementRun(run);
                    Striker.incrementBallsFaced();
                    if (BattingTeam.getScore() > BowlingTeam.getScore() && BowlingTeam.getScore() > 0) {
                        inningEnd = 1;
                        break;
                    }
                    if (run % 2 == 1) {
                        Player temp = Striker;
                        Striker = nonStriker;
                        nonStriker = temp;
                    } else {
                    }
                }
            }
            Player temp = Striker;
            Striker = nonStriker;
            nonStriker = temp;
            int index = BowlingTeam.listOfPlayers.indexOf(Bowler);
            index++;
            index = (int) (index % (BowlingTeam.getTotalPlayers()));
            Bowler = BowlingTeam.listOfPlayers.get(index);
            if (inningEnd == 1) {
                break;
            }
        }
        BattingTeam.setOverNumber(overnum);
        BattingTeam.setBallNumber(ballnum);
    }
}
