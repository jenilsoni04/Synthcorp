package org.example.MachineSystem.States;

import org.example.MachineSystem.MainMachines.Machine;

public interface MachineState {
    void start(Machine machine);
    void stop(Machine machine);
    String getStateName();
}
