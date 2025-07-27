package org.example.InventorySystem;

import org.example.Connection.ConnectionDB;

import java.sql.Connection;
import java.sql.SQLException;

public class InventoryConnector {

    private static InventorySystemFacade inventorySystemFacade;

    private InventoryConnector() {

    }

    public static InventorySystemFacade getInventorySystem() {
        if (inventorySystemFacade == null) {
            try {
                Connection connection = ConnectionDB.getConnection();
                MaterialManager manager = new MaterialManagerImpl(connection);
                MaterialQuery query = new MaterialQueryImpl(connection);
                inventorySystemFacade = new InventorySystemFacade(manager, query);
            } catch (SQLException e) {
                System.err.println("Failed to initialize InventorySystemFacade: " + e.getMessage());
            }
        }
        return inventorySystemFacade;
    }
}

