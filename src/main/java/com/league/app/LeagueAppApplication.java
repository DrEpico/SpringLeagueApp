package com.league.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;

@SpringBootApplication
public class LeagueAppApplication {

	public static void main(String[] args) throws SQLException {
		ApplicationContext context = SpringApplication.run(LeagueAppApplication.class, args);
//		FixtureGenerator fixGen = new FixtureGenerator();
		FixtureGenerator fixGen = context.getBean(FixtureGenerator.class);
//		Manager manager = new Manager();
		FixtureController fixCon = context.getBean(FixtureController.class);
	}

}
