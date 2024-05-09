package com.league.app;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Manager {
    //String url = "jdbc:sqlserver://localhost:1433;encrypt=true;databaseName=EAFC24;trustServerCertificate=true";
    //String username = "sql";
    private String password = "0000";

    public Manager(){
        printManagersAndTeams();
    }

    public void addManager() throws InputMismatchException, SQLException {
        Connection con = null;
        try {
            con = DriverManager.getConnection(LeagueApp.url, LeagueApp.username, password);
            con.setAutoCommit(false); // Start transaction
            String insertManagerSQL = "INSERT INTO managers (name, origin_id, league_admin) VALUES (?, ?, ?);";
            try (PreparedStatement ps = con.prepareStatement(insertManagerSQL, Statement.RETURN_GENERATED_KEYS)) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter manager's name: ");
                String managerName = scanner.nextLine();
                System.out.println("Enter manager's EA ID: ");
                String originID = scanner.nextLine();
                System.out.println("Is manager an admin? (true/false): ");
                boolean leagueAdmin = scanner.nextBoolean();
                scanner.nextLine(); // Consume newline character

                String checkManagerSQL = "SELECT COUNT(*) FROM managers WHERE origin_id = ?;";
                try (PreparedStatement checkOriginPs = con.prepareStatement(checkManagerSQL)) {
                    checkOriginPs.setString(1, originID);
                    ResultSet originResult = checkOriginPs.executeQuery();
                    originResult.next();
                    int count = originResult.getInt(1);
                    if (count > 0) {
                        System.out.println("Origin ID already exists. Skipping insertion.");
                        return; // Skip insertion!
                    }
                }

                ps.setString(1, managerName);
                ps.setString(2, originID);
                ps.setBoolean(3, leagueAdmin);
                ps.executeUpdate();

                // Retrieve generated manager ID
                ResultSet generatedId = ps.getGeneratedKeys();
                if (generatedId.next()) {
                    int newManagerId = generatedId.getInt(1);

                    //Update specified club with newly created manager ID
                    String updateClubSQL = "UPDATE clubs SET manager_id = ? WHERE club_name LIKE ?;";
                    try (PreparedStatement updateClubPs = con.prepareStatement(updateClubSQL)) {
                        System.out.println("Enter club name associated with the manager: ");
                        String clubName = scanner.nextLine();

                        String checkClubExistenceSQL = "SELECT COUNT(*) AS club_count FROM clubs WHERE club_name LIKE ?;";
                        try (PreparedStatement checkClubExistencePs  = con.prepareStatement(checkClubExistenceSQL)) {
                            checkClubExistencePs.setString(1, "%" + clubName + "%");
                            ResultSet clubExistenceResult = checkClubExistencePs.executeQuery();
                            if(clubExistenceResult.next()) {
                                int clubSearchCount = clubExistenceResult.getInt("club_count");
                                if(clubSearchCount < 1) {
                                    System.out.println("Club with that name does not exist.");
                                    return;
                                } else if (clubSearchCount > 1){
                                    System.out.println("More than one club was selected, please try a more specific name");
                                    return;
                                }
                            }

                        }

                        String checkClubManagerSQL = "SELECT manager_id FROM clubs WHERE club_name LIKE ? AND manager_id IS NOT NULL;";
                        try (PreparedStatement checkClubPs = con.prepareStatement(checkClubManagerSQL)) {
                            checkClubPs.setString(1, "%" + clubName + "%");
                            ResultSet clubResult = checkClubPs.executeQuery();
                            if (clubResult.next()) {
                                System.out.println("Club already has a manager assigned. Please choose a different club.");
                                return; //Skip insertion
                            }
                        }

                        updateClubPs.setInt(1, newManagerId);
                        updateClubPs.setString(2, "%" + clubName + "%");
                        updateClubPs.executeUpdate();
                        System.out.println("Manager assigned successfully (Generated manager ID: " + newManagerId + ")");
                    }
                }
                con.commit(); // Commit transaction if everything is successful
                System.out.println("Transaction committed successfully.");
            } catch (SQLException e) {
                con.rollback(); // Rollback transaction if an error occurs
                System.out.println("Transaction rolled back due to error: " + e.getMessage());
                e.printStackTrace();
            }
        } catch(SQLException e){
            System.out.println("Failed to establish database connection: " + e.getMessage());
            e.printStackTrace();
        } finally{
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
    }

    public void printManagersAndTeams() {
        try (Connection con = DriverManager.getConnection(LeagueApp.url, LeagueApp.username, password)) {
            String sql = "SELECT m.name AS manager_name, c.club_name AS team_assigned " +
                    "FROM managers m " +
                    "LEFT JOIN clubs c ON m.manager_id = c.manager_id " +
                    "ORDER BY m.name";

            try (PreparedStatement ps = con.prepareStatement(sql)) {
                ResultSet rs = ps.executeQuery();
                System.out.println("Managers and Teams:");
                System.out.println("+----------------------+---------------------+");
                System.out.println("|      Manager Name    |    Team Assigned    |");
                System.out.println("+----------------------+---------------------+");
                while (rs.next()) {
                    String managerName = rs.getString("manager_name");
                    String teamAssigned = rs.getString("team_assigned");
                    System.out.printf("| %-20s | %-19s |\n", managerName, teamAssigned != null ? teamAssigned : "None");
                }
                System.out.println("+----------------------+---------------------+");
            }
        } catch (SQLException e) {
            System.out.println("Failed to fetch managers and teams: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
