package org.example.Dashboard;

import org.example.Connection.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class WareHouseSystemFacade {
    private final Connection conn;

    public WareHouseSystemFacade() {
        try {
            this.conn = ConnectionDB.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private final Scanner sc = new Scanner(System.in);

    public void WareHouseMenu() {
        boolean menu = true;
        while (menu) {
            printMenu();
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> showReadyToUseLaptop();
                case 2 -> showDefectiveLaptop();
                case 3 -> menu = false;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("--- WareHouse Menu ---");
        System.out.println("1. View Ready to Use Laptops");
        System.out.println("2. View Defective Laptops");
        System.out.println("3. Return to Main Menu");
        System.out.print("Enter your choice: ");
    }

    public void showReadyToUseLaptop() {
        String query = "SELECT * FROM produced_laptops";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("---- Ready-to-Use Laptops ----");
            while (rs.next()) {
                int id = rs.getInt("id");
                int quantity = rs.getInt("quantity");
                String productionDate = rs.getString("production_date");

                System.out.printf("ID: %d, Quantity: %d, Production Date: %s%n", id, quantity, productionDate);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching produced laptops: " + e.getMessage());
        }
    }

    public void showDefectiveLaptop() {
        String query = "SELECT * FROM defective_products";
        try (PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("---- Defective Laptops ----");
            while (rs.next()) {
                int id = rs.getInt("id");
                String reason = rs.getString("reason");
                String defectedAt = rs.getString("timestamp");

                System.out.printf("ID: %d, Reason: %s, Defected At: %s%n", id, reason, defectedAt);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching defective laptops: " + e.getMessage());
        }
    }
}