package com.league.app;

import com.league.app.Club;
import com.league.app.Fixture;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class FixtureGenerator {
    public static String password = "0000";
    public ArrayList<Fixture> fixtures = new ArrayList<>();
    public static ArrayList<Club> clubs = new ArrayList<>();

    public FixtureGenerator() throws SQLException {
        printSeasonFixtures(generateFixtures(initClubs()));
    }

    /**
     * Identify clubs that have manager ID assigned, get their club ID's,
     * club names, and manager ID assigned to them to create an array list of Club objects.
     */
    public static ArrayList<Club> initClubs() throws SQLException {
        Connection con = null;
        try {
            con = DriverManager.getConnection(LeagueApp.url, LeagueApp.username, password);
            con.setAutoCommit(false); // Start transaction
            String getClubsSQL = "SELECT c.club_id, c.club_name, m.manager_id, m.origin_id, m.name FROM managers as m JOIN clubs as c ON m.manager_id = c.manager_id;"; // Modify this with your SQL query to retrieve clubs data
            try (PreparedStatement statement = con.prepareStatement(getClubsSQL)) {
                ResultSet resultSet = statement.executeQuery();
                while (resultSet.next()) {
                    int clubId = resultSet.getInt("club_id"); // Modify this with the column name for club ID
                    String clubName = resultSet.getString("club_name"); // Modify this with the column name for club name
                    int managerId = resultSet.getInt("manager_id"); // Modify this with the column name for manager ID
                    String originId = resultSet.getString("origin_id");
                    String managerName = resultSet.getString("name");

                    // Create Club object and add it to the clubs ArrayList
                    clubs.add(new Club(clubId, clubName, managerId, originId, managerName));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (con != null) {
                try {
                    con.setAutoCommit(true); // Reset auto-commit mode!
                    con.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close database connection: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        printClubs();
        return clubs;
    }

    /**
     * Print all elements of the clubs ArrayList.
     */
    private static void printClubs() {
        System.out.println("Printing all elements of the clubs ArrayList:");
        System.out.println("Club Name\t\tManager Name");
        System.out.println("-------------------------------------------");

        for (Club club : clubs) {
            System.out.printf("%-15s\t\t%-15s\n", club.clubName, club.managerName);
        }
    }

    /**
     * This utility method generates fixtures for the season
     * @param clubs List of club type objects
     * @return      Returns the generated season
     */
    public static Season generateFixtures(ArrayList<Club> clubs){
        Season season = new Season();

        for (int i = 0; i < 2; i++) {
            generateHalfSeasonFixtures(clubs, season);
            Collections.rotate(clubs, clubs.size() / 2);
        }

        return season;
    }

    private static void generateHalfSeasonFixtures(
            ArrayList<Club> clubs, Season season) {
        int totalTeams = clubs.size();
        int totalWeeks = totalTeams - 1;
        for(int week = 0; week < totalWeeks; week++) {
            Gameweek gameweek = new Gameweek(week + 1);
            for(int match = 0; match < totalTeams/2; match++){
                Fixture fixture = new Fixture(clubs.get(match), clubs.get(9 - match));
                gameweek.addFixture(fixture);
            }
            Collections.rotate(clubs, 1); //Rotate teams for next week
            season.addGameweek(gameweek);
        }
        System.out.println(season.getGameweeks().size());
    }

    public static void printSeasonFixtures(Season season) {
        System.out.println("Printing all fixtures of the season:");
        System.out.println("--------------------------------------------");
            int week = 1;
            for (Gameweek gameweek : season.getGameweeks()) {
                System.out.println("\nWeek " + week + ": ");
                for(Fixture fixture : gameweek.getFixtures()){
                    System.out.println(fixture.getFixture());
                }
                week++;
            }
            System.out.println();
        }
    }


