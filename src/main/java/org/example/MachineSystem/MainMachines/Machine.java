package org.example.MachineSystem.MainMachines;

import org.example.Enginner.Engineer;
import org.example.MachineSystem.States.IdleState;
import org.example.MachineSystem.States.MachineState;

import java.util.ArrayList;
import java.util.List;

public abstract class Machine {
    protected String machineId;
    protected MachineState state;
    private final List<Engineer> observers = new ArrayList<>();

    public Machine(String machineId) {
        this.machineId = machineId;
        this.state = new IdleState();
    }

    public String getMachineId() {
        return machineId;
    }

    public MachineState getState() {
        return state;
    }

    public void setState(MachineState state) {
        this.state = state;
    }

    public void start() {
        state.start(this);
    }

    public void stop() {
        state.stop(this);
    }

    public String getStateName() {
        return state.getStateName();
    }

    public void addObserver(Engineer observer) {
        observers.add(observer);
    }

    public void removeObserver(Engineer observer) {
        observers.remove(observer);
    }

    public void notifyObservers(String message) {
        for (Engineer observer : observers) {
            observer.onMachineError(machineId, message);
        }
    }

    public abstract void operate();
}
