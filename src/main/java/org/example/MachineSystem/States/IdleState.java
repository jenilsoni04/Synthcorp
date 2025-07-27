package org.example.MachineSystem.States;

import org.example.MachineSystem.MainMachines.Machine;

import java.util.Random;

public class IdleState implements MachineState {
    @Override
    public void start(Machine machine) {
        System.out.println("Attempting to start Machine " + machine.getMachineId() + " from Idle...");
        if (new Random().nextInt(100) < 20) {
            System.out.println("Machine " + machine.getMachineId() + " failed to start (Error).");
            machine.setState(new ErrorState());
            machine.notifyObservers("Machine " + machine.getMachineId() + " encountered a malfunction!");
        } else {
            System.out.println("Machine " + machine.getMachineId() + " started.");
            machine.setState(new ActiveState());
        }
    }

    @Override
    public void stop(Machine machine) {
        System.out.println("Machine " + machine.getMachineId() + " is already idle.");
    }

    @Override
    public String getStateName() {
        return "Idle";
    }
}
