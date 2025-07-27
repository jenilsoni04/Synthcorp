package org.example.ProductionSystem.Interfaces;

import java.sql.SQLException;

public interface ProductionStrategy {
    void produce() throws SQLException;
}
