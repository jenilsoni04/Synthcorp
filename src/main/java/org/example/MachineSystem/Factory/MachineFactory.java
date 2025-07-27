package org.example.MachineSystem.Factory;
import org.example.MachineSystem.MainMachines.Machine;

public interface MachineFactory {
    Machine createMachine(MachineType type);
    void Oldmachine(boolean useLegacy);

}