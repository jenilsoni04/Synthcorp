package org.example.MachineSystem.Decorators;

import org.example.MachineSystem.States.MachineState;
import org.example.MachineSystem.MainMachines.ToolMachine;


public abstract class MachineDecorator extends ToolMachine {
    protected ToolMachine decoratedMachine;

    public MachineDecorator(ToolMachine decoratedMachine) {
        super(decoratedMachine.getMachineId());
        this.decoratedMachine = decoratedMachine;
    }

    @Override
    public void start() {
        decoratedMachine.start();
    }

    @Override
    public void stop() {
        decoratedMachine.stop();
    }

    @Override
    public MachineState getState() {
        return decoratedMachine.getState();
    }

    @Override
    public void setState(MachineState state) {
        decoratedMachine.setState(state);
    }

    @Override
    public void addObserver(org.example.Enginner.Engineer observer) {
        decoratedMachine.addObserver(observer);
    }

    @Override
    public void removeObserver(org.example.Enginner.Engineer observer) {
        decoratedMachine.removeObserver(observer);
    }

    @Override
    public void notifyObservers(String message) {
        decoratedMachine.notifyObservers(message);
    }
}
