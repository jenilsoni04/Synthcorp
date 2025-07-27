package org.example.ProductionSystem.ProductionMethods;

import org.example.InventorySystem.InventoryLogger;
import org.example.Laptop.LaptopDAO;
import org.example.Production.ProductionManager;
import org.example.Production.ProductionResult;
import org.example.ProductionSystem.Interfaces.ProductionStrategy;
import org.example.MachineSystem.Factory.MachineFactory;
import org.example.MachineSystem.Factory.MachineFactoryImpl;
import org.example.InventorySystem.InventoryLogger;
import java.sql.SQLException;
import java.util.List;

public class CustomBatchStrategy implements ProductionStrategy {

    private final int totalBatch;
    private final int noOfComputerPerBatch;
    private final MachineFactory factory;
    private final ProductionManager manager;
    private InventoryLogger logger;


    public CustomBatchStrategy(int totalBatch, int noOfComputerPerBatch, MachineFactoryImpl factory, List<String> decorators) {
        this.totalBatch = totalBatch;
        this.noOfComputerPerBatch = noOfComputerPerBatch;
        this.logger=new InventoryLogger();
        this.factory= factory;
        this.manager = new ProductionManager(factory, totalBatch * noOfComputerPerBatch,decorators);
    }

        @Override
        public void produce() throws SQLException {
            int overallPassed = 0;
            int overallFailed = 0;
            int counter=0;

            for (int i = 0; i < totalBatch; i++) {
                int passedCount = 0;
                int failedCount = 0;

                for (int j = 0; j < noOfComputerPerBatch; j++) {
                    ProductionResult result = manager.startProduction();
                    if (result.isPassedQC()) {
                        passedCount++;
                    } else {
                        failedCount++;
                    }
                    counter++;
                }
                logger.inventoryUsageLog(counter);
                counter=0;
                overallPassed += passedCount;
                overallFailed += failedCount;

                if (passedCount > 0)
                    LaptopDAO.insertProducedLaptops(passedCount);
                if (failedCount > 0)
                    LaptopDAO.insertDefectiveLaptop(failedCount, "Failed QC in batch");

                System.out.println("Batch " + (i + 1) + " completed. Passed: " + passedCount + ", Failed: " + failedCount);
            }

            System.out.println();
            System.out.println("Custom Batch Production Completed. Total Passed: " + overallPassed + ", Failed: " + overallFailed);
        }
    }

