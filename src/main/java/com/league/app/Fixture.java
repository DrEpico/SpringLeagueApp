package com.league.app;

import com.league.app.Club;

public class Fixture {
    Club homeTeam;
    Club awayTeam;

    //todo: an object might be needed to handle match performances and etc.
    //maybe read players from clubs and etc
    public Fixture(Club one, Club two){
//        if(firstHalf) {
            homeTeam = one;
            awayTeam = two;
//        } else {
//            homeTeam = two;
//            awayTeam = one;
//        }
    }

    public String getFixture(){
        return homeTeam.clubName + " vs " + awayTeam.clubName;
    }


}

