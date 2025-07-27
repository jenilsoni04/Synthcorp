package org.example.MachineSystem.WorkFlowRobots;

import org.example.InventorySystem.InventoryConnector;
import org.example.InventorySystem.InventorySystemFacade;
import org.example.Laptop.LaptopProductionPlan;
import org.example.MachineSystem.States.ErrorState;
import org.example.MachineSystem.MainMachines.WorkFlowRobot;

public class AssemblyRobot extends WorkFlowRobot {
    public AssemblyRobot(String machineId) {
        super(machineId);
    }

    @Override
    public void operate() {
        start();
        if (getState() instanceof ErrorState) return;
        System.out.println("Assembling components...");

        InventorySystemFacade inventory = InventoryConnector.getInventorySystem();

        String[] materials = {"Motherboard", "Screen", "RAM", "Keyboard", "Switch", "Camera"};
        for (String material : materials) {
            int needed = LaptopProductionPlan.getQuantityFor(material);
            if (!inventory.hasSufficientMaterial(material, needed)) {
                System.out.println("Not enough " + material + ". Halting.");
                stop();
                System.exit(1);
                return;
            }
            inventory.useMaterial(material, needed);
        }

        stop();
        System.out.println("Assembly complete.");
    }
}
