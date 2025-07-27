package org.example.MachineSystem.Factory;

import org.example.MachineSystem.Adapter.WeildingAdapter;
import org.example.MachineSystem.MainMachines.Machine;
import org.example.MachineSystem.ToolMachines.*;
import org.example.MachineSystem.WorkFlowRobots.*;

import java.util.HashMap;
import java.util.Map;

public class MachineFactoryImpl implements MachineFactory {
    private final Map<MachineType, Machine> machineCache = new HashMap<>();
    private boolean useLegacy = false;

    @Override
    public void Oldmachine(boolean useLegacy) {
        this.useLegacy = useLegacy;
        System.out.println("Legacy mode set to: " + this.useLegacy + " (instance: " + this + ")");
    }

    @Override
    public Machine createMachine(MachineType type) {
        if (machineCache.containsKey(type)) {
            return machineCache.get(type);
        }

        Machine machine;
        switch (type) {
            case CUTTING -> machine = new CuttingMachine("CuttingMachine-1");
            case DRILLING -> machine = new DrillingMachine("DrillingMachine-1");
            case WELDING -> machine = useLegacy
                    ? new WeildingAdapter("LegacyWelding-1")
                    : new WeldingMachine("WeldingMachine-1");
            case ASSEMBLY -> machine = new AssemblyRobot("AssemblyRobot-1");
            case QUALITY_CONTROL -> machine = new QualityControlRobot("QualityControlRobot-1");
            case PACKAGING -> machine = new PackagingRobot("PackagingRobot-1");
            default -> throw new IllegalArgumentException("Unknown machine type: " + type);
        }

        machineCache.put(type, machine);
        return machine;
    }
}
