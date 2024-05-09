package com.league.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.league.app.FixtureGenerator.generateFixtures;
import static com.league.app.FixtureGenerator.initClubs;

@RestController
public class FixtureController {

    @Autowired
    private FixtureGenerator fixtureGenerator;

    @GetMapping("/")
    public String generateFormattedFixtures() throws SQLException {
        StringBuilder formattedFixtures = new StringBuilder();
        Season season = generateFixtures(initClubs());
        int week = 1;
        for (Gameweek gameweek : season.getGameweeks()) {
            formattedFixtures.append("\nWeek ").append(week).append(":\n");
            for (Fixture fixture : gameweek.getFixtures()) {
                formattedFixtures.append(fixture.homeTeam.clubName).append(" vs ").append(fixture.awayTeam.clubName).append("\n");
            }
            week++;
        }
        return formattedFixtures.toString();
    }
}
