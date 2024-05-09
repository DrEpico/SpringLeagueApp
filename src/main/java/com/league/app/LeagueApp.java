package com.league.app;

import com.league.app.Club;

import java.sql.*;
import java.util.ArrayList;

/**
 * Main class
 */
public class LeagueApp {
    public static String url = "jdbc:sqlserver://localhost:1433;encrypt=true;databaseName=EAFC24;trustServerCertificate=true";
    public static String username = "sql";
//    public static String password = "0000";

    int season = 1;
    ArrayList<Club> teams = new ArrayList<>();

//    public void progressSeason(){
//        season++;
//        resetSeasonData();
//        addPlayerAge();
//    }

    //todo: player upgreade based on position


//    public void resetSeasonData(){
//        for(Club team : teams){
//            team.winLoss.setSeasonWins(0);
//            team.winLoss.setSeasonDraws(0);
//            team.winLoss.setSeasonLosses(0);
//            team.winLoss.setSeasonMatches(0);
//            for(Player player : team.players){
//                player.setSeasonGoals(0);
//                player.setSeasonAssists(0);
//                player.setSeasonCleansheets(0);
//            }
//        }
//    }

//    public void addPlayerAge(){
//        for(Club team : teams){
//            for(Player player : team.players){
//                player.addAge();
//            }
//        }
//    }

    public static void main(String[] args) throws SQLException {
//        Manager manager = new Manager();
//        manager.addManager();
        FixtureGenerator fixGen = new FixtureGenerator();

    }
}


//public static void main(String[] args) throws SQLException {
//    Tesseract tesseract = new Tesseract();
//    try {
//        tesseract.setDatapath("C:\\Users\\adada\\Downloads\\Tess4J-3.4.8-src\\Tess4J\\tessdata");
//        // the path of your .jpg, .png or .gif file
//        String text = tesseract.doOCR(new File("C:\\Users\\adada\\Documents\\ShareX\\Screenshots\\2024-01\\FC24_byUzkGEoE2.png"));
//        System.out.println(text);
//    } catch (TesseractException e) {
//        e.printStackTrace();
//    }
//}