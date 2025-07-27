package org.example.MachineSystem.WorkFlowRobots;

import org.example.InventorySystem.InventoryConnector;
import org.example.InventorySystem.InventorySystemFacade;
import org.example.Laptop.LaptopProductionPlan;
import org.example.MachineSystem.States.ErrorState;
import org.example.MachineSystem.MainMachines.WorkFlowRobot;

public class PackagingRobot extends WorkFlowRobot{
    public PackagingRobot(String machineId) {
        super(machineId);
    }

    @Override
    public void operate() {
        start();
        if (getState() instanceof ErrorState) return;
        System.out.println("Packaging laptop...");

        InventorySystemFacade inventory = InventoryConnector.getInventorySystem();
        int boxesNeeded = LaptopProductionPlan.getQuantityFor("Box");

        if (!inventory.hasSufficientMaterial("Box", boxesNeeded)) {
            System.out.println("Not enough Boxes in inventory. Halting.");
            stop();
            System.exit(1);
            return;
        }

        inventory.useMaterial("Box", boxesNeeded);

        stop();
        System.out.println("Packaging complete. Laptop ready for delivery!");
    }
}
