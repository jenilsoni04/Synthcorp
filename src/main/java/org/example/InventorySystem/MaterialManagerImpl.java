package org.example.InventorySystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MaterialManagerImpl implements MaterialManager{
    private final Connection connection;

    public MaterialManagerImpl(Connection connection) {
        this.connection = connection;
    }
    public void addmaterial(RawMaterial material)
    {
        String sql="INSERT INTO raw_materials (name, quantity, min_stock_req) VALUES (?, ?, ?)";
        try(PreparedStatement stmt= connection.prepareStatement(sql))
        {
            stmt.setString(1, material.getName());
            stmt.setInt(2, material.getQuantity());
            stmt.setInt(3, material.getMin_quantity_req());
            int rows = stmt.executeUpdate();

            if (rows > 0) {
                System.out.println("Material added successfully!");
            } else {
                System.out.println("Failed to add material.");
            }
        }
        catch (SQLException e)
        {
            System.err.println("Error while adding material: " + e.getMessage());
        }
    }
    public void useMaterial(String name, int amount) {
        String sql = "UPDATE raw_materials SET quantity = quantity - ? WHERE name = ? AND quantity >= ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, amount);
            stmt.setString(2, name);
            stmt.setInt(3, amount);
            int rows = stmt.executeUpdate();

            System.out.println(rows > 0 ? "Material used successfully." : "Not enough quantity or material not found.");

        } catch (SQLException e) {
            System.err.println("Error while using material: " + e.getMessage());
        }
    }

    public void updateMaterial(String name, int newQuantity, int newMin) {
        String sql = "UPDATE raw_materials SET quantity = ?, min_stock_req = ? WHERE name = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, newQuantity);
            stmt.setInt(2, newMin);
            stmt.setString(3, name);
            int rows = stmt.executeUpdate();

            System.out.println(rows > 0 ? "Material updated." : "Material not found.");

        } catch (SQLException e) {
            System.err.println("Error while updating material: " + e.getMessage());
        }
    }

    public void deleteMaterial(String name) {
        String sql = "DELETE FROM raw_materials WHERE name = ?";

        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, name);
            int rows = stmt.executeUpdate();

            System.out.println(rows > 0 ? "Material deleted." : "Material not found.");

        } catch (SQLException e) {
            System.err.println("Error while deleting material: " + e.getMessage());
        }
    }

}
