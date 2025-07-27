package org.example.Production;

import org.example.Connection.ConnectionDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductionPerformance {
    private int totalProduced;
    private int totalDefective;

    public void loadDataForDate(String date) {
        this.totalProduced = fetchProducedLaptops(date);
        this.totalDefective = fetchDefectiveLaptops(date);
    }

    private int fetchProducedLaptops(String date) {
        String sql = "SELECT SUM(quantity) FROM produced_laptops WHERE production_date::date = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            System.err.println("Error fetching produced laptops: " + e.getMessage());
        }
        return 0;
    }

    private int fetchDefectiveLaptops(String date) {
        System.out.println("Fetching defective laptops for date: " + date);
        String sql = "SELECT SUM(quantity) FROM defective_products WHERE timestamp::date = ?";
        try (Connection conn = ConnectionDB.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setDate(1, java.sql.Date.valueOf(date));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching defective laptops: " + e.getMessage());
        }
        return 0;
    }

    public int getTotalProduced() {
        return totalProduced;
    }

    public int getTotalDefective() {
        return totalDefective;
    }

    public double getDefectivePercentage() {
        if (totalProduced == 0) return 0.0;
        return (totalDefective * 100.0) / (totalProduced+totalDefective);
    }

    public double getSuccessPercentage() {
        if (totalProduced == 0) return 0.0;
        return (totalProduced * 100.0) / (totalProduced+totalDefective);
    }
}
