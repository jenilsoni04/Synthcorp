package org.example.MachineSystem.States;

import org.example.MachineSystem.MainMachines.Machine;

public class ActiveState implements MachineState {
    @Override
    public void start(Machine machine) {
        System.out.println("Machine " + machine.getMachineId() + " is already active.");
    }

    @Override
    public void stop(Machine machine) {
        System.out.println("Stopping Machine " + machine.getMachineId() + "...");
        machine.setState(new IdleState());
    }

    @Override
    public String getStateName() {
        return "Active";
    }
}
