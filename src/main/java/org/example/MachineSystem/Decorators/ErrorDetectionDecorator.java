package org.example.MachineSystem.Decorators;

import org.example.MachineSystem.MainMachines.ToolMachine;

public class ErrorDetectionDecorator extends MachineDecorator {

    public ErrorDetectionDecorator(ToolMachine decoratedMachine) {
        super(decoratedMachine);
    }

    @Override
    public void operate() {
        System.out.println("Error Checking system before operation...");
        decoratedMachine.operate();
        System.out.println("No issues detected.");
    }
}
