package org.example.MachineSystem.ToolMachines;

import org.example.InventorySystem.InventoryConnector;
import org.example.InventorySystem.InventorySystemFacade;
import org.example.Laptop.LaptopProductionPlan;
import org.example.MachineSystem.States.ErrorState;
import org.example.MachineSystem.MainMachines.ToolMachine;


public class CuttingMachine extends ToolMachine {
    public CuttingMachine(String machineId) {
        super(machineId);
    }

    @Override
    public void operate() {
        start();
        if (getState() instanceof ErrorState) return;

        System.out.println("Cutting laptop frame...");

        InventorySystemFacade inventory = InventoryConnector.getInventorySystem();
        int aluminumNeeded = LaptopProductionPlan.getQuantityFor("Aluminum");

        if (!inventory.hasSufficientMaterial("Aluminum", aluminumNeeded)) {
            System.out.println("Not enough Aluminum in inventory. Machine " + getMachineId() + " stopping...");
            stop();
            setState(new ErrorState());
            return;
        }

        inventory.useMaterial("Aluminum", aluminumNeeded);

        stop();
        System.out.println("Cutting complete by " + getMachineId());
    }
}
