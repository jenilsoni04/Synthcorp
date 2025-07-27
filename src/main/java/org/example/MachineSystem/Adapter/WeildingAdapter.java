package org.example.MachineSystem.Adapter;

import org.example.MachineSystem.MainMachines.ToolMachine;
import org.example.MachineSystem.OldMachine.OldWeildingMachine;

public class WeildingAdapter extends ToolMachine {
    private final OldWeildingMachine oldWeldingSystem;

    public WeildingAdapter(String machineId) {
        super(machineId);
        this.oldWeldingSystem = new OldWeildingMachine();
    }

    @Override
    public void operate() {
        System.out.println("Using Adapter for Legacy Welding System...");
        oldWeldingSystem.powerOn();
        oldWeldingSystem.weld();
        oldWeldingSystem.powerOff();
        System.out.println("Legacy Welding complete.");
    }
}