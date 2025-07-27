package org.example.MachineSystem.ToolMachines;

import org.example.MachineSystem.States.ErrorState;
import org.example.MachineSystem.MainMachines.ToolMachine;


public class DrillingMachine extends ToolMachine {
    public DrillingMachine(String machineId) {
        super(machineId);
    }

    @Override
    public void operate() {
        start();
        if (getState() instanceof ErrorState) return;

        System.out.println("Drilling holes into the laptop frame...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println("Drilling interrupted for " + getMachineId());
            e.printStackTrace();
        }

        stop();
        System.out.println("Drilling complete by " + getMachineId());
    }
}
