package org.example.Laptop;

import org.example.Connection.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class LaptopDAO {

    public static void insertProducedLaptops(int quantity) {
        String sql = "INSERT INTO produced_laptops (quantity) VALUES (?)";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionDB.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, quantity);
            stmt.executeUpdate();
            System.out.println("Saved " + quantity + " laptops to the database.");
        } catch (SQLException e) {
            System.err.println("Error saving produced laptops: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing statement: " + e.getMessage());
            }
        }
    }

    public static void insertDefectiveLaptop(int quantity, String reason) {
        String sql = "INSERT INTO defective_products (quantity, reason) VALUES (?, ?)";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionDB.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, quantity);
            stmt.setString(2, reason);
            stmt.executeUpdate();
            System.out.println("Defective laptop recorded: " + reason);
        } catch (SQLException e) {
            System.err.println("Error saving defective laptop: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing statement: " + e.getMessage());
            }
        }
    }
}
