package com.league.app;

import java.util.ArrayList;
import java.util.List;

public class Season {
    private List<Gameweek> gameweeks;
    int seasonNumber = 1;

    public Season() {
        this.gameweeks = new ArrayList<>();
    }

    //todo: implement a more efficient search algorithm

    /**
     * Searches for Gameweek by week number using:
     * @param weekNumber
     * @return Gameweek object
     */
    public Gameweek getGameweekByWeek(int weekNumber) {
        for (Gameweek gameweek : gameweeks) {
            if (gameweek.getWeekNumber() == weekNumber) {
                return gameweek;
            }
        }
        return null; // Return null if the week is not found
    }

    /**
     * @return A list of Gameweek objects
     */
    public List<Gameweek> getGameweeks() {
        return gameweeks;
    }

    public void addGameweek(Gameweek gameweek) {
        gameweeks.add(gameweek);
    }
}
