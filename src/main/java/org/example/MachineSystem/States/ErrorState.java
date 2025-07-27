package org.example.MachineSystem.States;


import org.example.MachineSystem.MainMachines.Machine;

public class ErrorState implements MachineState {
    @Override
    public void start(Machine machine) {
        System.out.println("Cannot start Machine " + machine.getMachineId() + " — it is in Error state.");
    }

    @Override
    public void stop(Machine machine) {
        System.out.println("Resetting Machine " + machine.getMachineId() + " to Idle...");
        machine.setState(new IdleState());
    }

    @Override
    public String getStateName() {
        return "Error";
    }
}
