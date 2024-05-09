package com.league.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;

@SpringBootApplication
public class LeagueApp {
	public static final String url = "jdbc:sqlserver://localhost:1433;encrypt=true;databaseName=EAFC24;trustServerCertificate=true";
	public static final String username = "sql";

	public static void main(String[] args) throws SQLException {
		ApplicationContext context = SpringApplication.run(LeagueApp.class, args);
//		FixtureGenerator fixGen = new FixtureGenerator();
		FixtureGenerator fixGen = context.getBean(FixtureGenerator.class);
//		Manager manager = new Manager();
		FixtureController fixCon = context.getBean(FixtureController.class);


	}
}
