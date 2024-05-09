package com.league.app;

import com.league.app.Club;
import com.league.app.Position;
import com.league.app.Stats;

public class Player {
    private String name;
    private Position position;
    private int age;
    private Club club;
    private Stats stats = new Stats();
    private int value;
    private int weeklyWage;
    private int annualSalary;

    private int totalGoals;
    private int seasonGoals;
    private int totalAssists;
    private int seasonAssists;
    private int totalCleanSheets;
    private int seasonCleansheets;
    private int upgradePoints;

    public Player(String name, Position position, int age, Club club, Stats stats, int value, int weeklyWage) {
        this.name = name;
        this.position = position;
        this.age = age;
        this.club = club;
        this.stats = stats;
        this.value = value;
        this.weeklyWage = weeklyWage;
        this.annualSalary = weeklyWage*52;

    }

    /**
     * Adds 1 to the player age.
     */
    public void addAge(){
        age++;
    }

    /**
     * Calculates upgrade points for players in different positions.
     *
     * Goals or assists being multiplied by 2 means 1 point and the
     * ones without the x2 are the same as 0.5
     *
     * @param player The player instance that is going to have its upgrade points calculated.
     */
    public void calcUpgradePointsByPosition(Player player){
        //GK and CB -> CS 1.0
        if(player.position == Position.GK || player.position == Position.CB){
            if(stats.OVR < 80) {
                player.upgradePoints += player.seasonCleansheets*2;
            } else {
                player.upgradePoints += player.seasonCleansheets;
            }
        //LB and RB -> CS 1.0 / assist 0.5
        } else if(player.position == Position.LB || player.position == Position.RB) {
            if(stats.OVR < 80) {
                player.upgradePoints += player.seasonCleansheets*2;
            } else {
                player.upgradePoints += player.seasonCleansheets;
            }
            player.upgradePoints += player.seasonAssists;
        //LWB and RWB -> CS 1.0 / goal 0.5 / assist 0.5
        }else if (position == Position.LWB || position == Position.RWB) {
            if (stats.OVR < 80) {
                player.upgradePoints += player.seasonCleansheets * 2;
            } else {
                player.upgradePoints += player.seasonCleansheets;
            }
            player.upgradePoints += player.seasonGoals;
            player.upgradePoints += player.seasonAssists;
        //CDM -> CS 1.0 / assist 0.5
        } else if (player.position == Position.CDM){
            if (stats.OVR < 80) {
                player.upgradePoints += player.seasonCleansheets * 2;
            } else {
                player.upgradePoints += player.seasonCleansheets;
            }
            player.upgradePoints += player.seasonAssists;
        //CM -> goal 0.5 / assist 1.0
        } else if (position == Position.CM){
            player.upgradePoints += player.seasonGoals;
            player.upgradePoints += player.seasonAssists*2;
        //LM and RM -> goal 0.5 / assist 1.0
        } else if (position == Position.LM || position == Position.RM){
            player.upgradePoints += player.seasonGoals;
            player.upgradePoints += player.seasonAssists*2;
        //CAM -> goal 0.5 / assist 1.0
        } else if (position == Position.CAM){
            player.upgradePoints += player.seasonGoals;
            player.upgradePoints += player.seasonAssists*2;
        //CF, RW and LW -> goal 1.0 / assist 0.5
        } else if (position == Position.CF || position == Position.RW || position == Position.LW){
            player.upgradePoints += player.seasonGoals*2;
            player.upgradePoints += player.seasonAssists;
        //ST -> goal 1.0
        } else if (position == Position.ST){
            player.upgradePoints += player.seasonGoals*2;
        }
    }

    public void useUpgradePoints(){
        stats.OVR += 1;
        upgradePoints -= 4;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getWeeklyWage() {
        return weeklyWage;
    }

    public void setWeeklyWage(int weeklyWage) {
        this.weeklyWage = weeklyWage;
    }

    public int getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(int annualSalary) {
        this.annualSalary = annualSalary;
    }

    public int getTotalGoals() {
        return totalGoals;
    }

    public void setTotalGoals(int totalGoals) {
        this.totalGoals = totalGoals;
    }

    public int getSeasonGoals() {
        return seasonGoals;
    }

    public void setSeasonGoals(int seasonGoals) {
        this.seasonGoals = seasonGoals;
    }

    public int getTotalAssists() {
        return totalAssists;
    }

    public void setTotalAssists(int totalAssists) {
        this.totalAssists = totalAssists;
    }

    public int getSeasonAssists() {
        return seasonAssists;
    }

    public void setSeasonAssists(int seasonAssists) {
        this.seasonAssists = seasonAssists;
    }

    public int getTotalCleanSheets() {
        return totalCleanSheets;
    }

    public void setTotalCleanSheets(int totalCleanSheets) {
        this.totalCleanSheets = totalCleanSheets;
    }

    public int getSeasonCleansheets() {
        return seasonCleansheets;
    }

    public void setSeasonCleansheets(int seasonCleansheets) {
        this.seasonCleansheets = seasonCleansheets;
    }
}
