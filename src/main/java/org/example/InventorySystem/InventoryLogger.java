package org.example.InventorySystem;

import org.example.Connection.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

public class InventoryLogger {

    private final int unitPerComponent = 1;

    public void inventoryUsageLog(int count) {
        String description = generateUsageMaterialDescription(count);
        Timestamp now = new Timestamp(System.currentTimeMillis());

        String sql = "INSERT INTO inventory_logs " +
                "(product_used_description, log_date, log_timestamp) " +
                "VALUES (?, CURRENT_DATE, ?)";

        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = ConnectionDB.getConnection();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, description);
            stmt.setTimestamp(2, now);

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Inventory log inserted");
            }

        } catch (SQLException e) {
            System.out.println("Could not insert inventory log. Error: " + e.getMessage());
        } finally {
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException e) {
                System.err.println("Error closing statement: " + e.getMessage());
            }
        }
    }

    private String generateUsageMaterialDescription(int count) {
        int aluminum = count * unitPerComponent * 5;
        int motherboards = count * unitPerComponent;
        int screens = count * unitPerComponent;
        int RAM = count * unitPerComponent;
        int camera = count * unitPerComponent;
        int keyboard = count * unitPerComponent;
        int switches = count * unitPerComponent;
        int box = count * unitPerComponent;

        return "Used " + aluminum + " Aluminum, " +
                motherboards + " Motherboard, " +
                screens + " Screens, " +
                RAM + " RAM, " +
                keyboard + " Keyboard, " +
                switches + " Switch, " +
                camera + " Camera and " +
                box + " Box in this production";
    }
}
