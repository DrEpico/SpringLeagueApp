package com.league.app;

import java.util.ArrayList;
import java.util.List;

public class Gameweek {
    private int weekNumber;
    private List<Fixture> fixtures;

    public Gameweek(int weekNumber) {
        this.weekNumber = weekNumber;
        this.fixtures = new ArrayList<>();
    }

    public int getWeekNumber() {
        return weekNumber;
    }

    public List<Fixture> getFixtures() {
        return fixtures;
    }

    public void addFixture(Fixture fixture) {
        fixtures.add(fixture);
    }

    public void printGameweek(){
        for (Fixture fixture : fixtures) {
            System.out.println(fixture.homeTeam.clubName + " vs " + fixture.awayTeam.clubName);
        }
    }
}
