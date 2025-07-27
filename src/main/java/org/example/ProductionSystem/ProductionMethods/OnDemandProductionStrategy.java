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

public class OnDemandProductionStrategy implements ProductionStrategy {


    private final int quantity = 1;
    private final MachineFactory factory;
    private final ProductionManager manager;
    private InventoryLogger logger;

    public OnDemandProductionStrategy(MachineFactoryImpl factory, List<String> decorators) {
        this.factory = factory;
        this.logger=new InventoryLogger();
        this.manager = new ProductionManager(factory, quantity,decorators);
    }


        @Override
        public void produce() throws SQLException {
            ProductionResult result = manager.startProduction();

            if (result.isPassedQC()) {
                LaptopDAO.insertProducedLaptops(quantity);
                System.out.println("On-Demand Laptop Passed QC and was packaged.");
            } else {
                LaptopDAO.insertDefectiveLaptop(quantity, "Failed QC in on-demand");
                System.out.println("On-Demand Laptop Failed QC.");
            }
            logger.inventoryUsageLog(1);
        }
    }

