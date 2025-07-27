package org.example.InventorySystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MaterialQueryImpl implements MaterialQuery{
    private final Connection connection;

    public MaterialQueryImpl(Connection connection) {
        this.connection = connection;
    }
    public void displayMaterials() {
        String sql = "SELECT * FROM raw_materials";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            ResultSet rs = stmt.executeQuery();
            System.out.println();
            System.out.println("Inventory:");
            while (rs.next()) {
                System.out.println("Name: " + rs.getString("name") +
                        ", Quantity: " + rs.getInt("quantity") +
                        ", Min Stock: " + rs.getInt("min_stock_req"));
            }

        } catch (SQLException e) {
            System.err.println("Error while fetching materials: " + e.getMessage());
        }
    }
    public boolean hasSufficientMaterial(String material, int requiredQty) {
        String sql = "SELECT quantity FROM raw_materials WHERE name = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, material);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int currentQty = rs.getInt("quantity");
                return currentQty >= requiredQty;
            } else {
                System.out.println("Material '" + material + "' not found in inventory.");
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Error checking material availability: " + e.getMessage());
            return false;
        }
    }
    public int getMaterialQuantity(String material) {
        String sql = "SELECT quantity FROM raw_materials WHERE name = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, material);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getInt("quantity");
            } else {
                System.out.println("Material '" + material + "' not found in inventory.");
                return 0;
            }

        } catch (SQLException e) {
            System.err.println("Error fetching material quantity: " + e.getMessage());
            return 0;
        }
    }
}
