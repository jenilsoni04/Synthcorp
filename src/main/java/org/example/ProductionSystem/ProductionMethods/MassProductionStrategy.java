package org.example.ProductionSystem.ProductionMethods;

import org.example.InventorySystem.InventoryLogger;
import org.example.MachineSystem.Factory.MachineFactory;
import org.example.MachineSystem.Factory.MachineFactoryImpl;
import org.example.Production.ProductionResult;
import org.example.ProductionSystem.Interfaces.ProductionStrategy;
import org.example.Production.ProductionManager;
import org.example.Laptop.LaptopDAO;

import java.sql.SQLException;
import java.util.List;

public class MassProductionStrategy implements ProductionStrategy {

    private final int quantity;
    private final MachineFactory factory;
    private final ProductionManager manager;
    private InventoryLogger logger;

    public MassProductionStrategy(int quantity, MachineFactoryImpl factory, List<String> decorators) {
        this.quantity = quantity;
        this.factory = factory;
        this.logger=new InventoryLogger();
        this.manager = new ProductionManager(factory, quantity,decorators);
    }

    @Override
    public void produce() throws SQLException {
        int passedCount = 0;
        int failedCount = 0;

        for (int i = 0; i < quantity; i++) {
            ProductionResult result = manager.startProduction();
            if (result.isPassedQC()) {
                passedCount++;
            } else {
                failedCount++;
            }
        }
        logger.inventoryUsageLog(quantity);

        if (passedCount > 0)
            LaptopDAO.insertProducedLaptops(passedCount);
        if (failedCount > 0)
            LaptopDAO.insertDefectiveLaptop(failedCount, "Failed QC in mass production");

        System.out.println("Mass Production completed. Passed: " + passedCount + ", Failed: " + failedCount);
    }




}