package org.example.MachineSystem.ToolMachines;

import org.example.MachineSystem.States.ErrorState;
import org.example.MachineSystem.MainMachines.ToolMachine;


public class WeldingMachine extends ToolMachine {
    public WeldingMachine(String machineId) {
        super(machineId);
    }

    @Override
    public void operate() {
        start();
        if (getState() instanceof ErrorState) return;

        System.out.println("🔩 Welding machine " + getMachineId() + " is welding the frame...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println("Welding interrupted on machine " + getMachineId());
            e.printStackTrace();
        }

        stop();
        System.out.println("Welding complete on " + getMachineId());
    }
}
