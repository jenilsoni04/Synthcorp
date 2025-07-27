package org.example.MachineSystem.Decorators;

import org.example.MachineSystem.MainMachines.ToolMachine;

public class EnergyEfficientModeDecorator extends MachineDecorator {

    public EnergyEfficientModeDecorator(ToolMachine decoratedMachine) {
        super(decoratedMachine);
    }

    @Override
    public void start() {
        System.out.println("EnergySaver Starting in low-power mode...");
        super.start();
    }

    @Override
    public void operate() {
        System.out.println("EnergySaver Operating efficiently...");
        decoratedMachine.operate();
    }

    @Override
    public void stop() {
        super.stop();
        System.out.println("EnergySaver Shutting down in eco mode.");
    }
}
