package com.league.app;

import java.util.ArrayList;

public class Club {
    public int clubId;
    public String clubName;
    public int managerId;
    public String originId;
    public String managerName;
//    ArrayList<Player> players;

    public Club(int clubId, String name, int managerId, String originId, String managerName){
        this.clubId = clubId;
        this.clubName = name;
        this.managerId = managerId;
        this.originId = originId;
        this.managerName = managerName;
//        players = new ArrayList<>();
    }

//    public void addPlayer(Player player){
//        players.add(player);
//    }
}




