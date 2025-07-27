package org.example.Production;

import org.example.Enginner.Engineer;
import org.example.Laptop.LaptopProductionPlan;
import org.example.MachineSystem.Decorators.ChooseDecorator;
import org.example.MachineSystem.MainMachines.Machine;
import org.example.MachineSystem.MainMachines.ToolMachine;
import org.example.MachineSystem.MainMachines.WorkFlowRobot;
import org.example.MachineSystem.Factory.MachineFactory;
import org.example.MachineSystem.Factory.MachineType;
import org.example.MachineSystem.WorkFlowRobots.QualityControlRobot;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductionManager {
    private final MachineFactory factory;
    private final int laptopQuantity;
    private final List<String> decorators;
    private final List<Machine> allMachines = new ArrayList<>();
    private final Engineer engineer = new Engineer("Anuj");

    public ProductionManager(MachineFactory factory, int laptopQuantity, List<String> decorators) {
        this.factory = factory;
        this.laptopQuantity = laptopQuantity;
        this.decorators = decorators;
        LaptopProductionPlan.setLaptopQuantity(laptopQuantity);
    }

    public ProductionResult startProduction() throws SQLException {
        System.out.println("\n Starting Tool Machine Workflow for " + laptopQuantity + " laptops...");

        if (!MaterialChecker.canProduceAllLaptops(laptopQuantity)) {
            System.out.println("Production halted due to insufficient materials.");
            return new ProductionResult(false);
        }

        List<Machine> toolMachines = initializeToolMachines();
        if (!runToolMachineWorkflow(toolMachines)) {
            return new ProductionResult(false);
        }

        Machine assembly = createAndTrackMachine(MachineType.ASSEMBLY);
        assembly.operate();

        WorkFlowRobot qcRobot = (WorkFlowRobot) createAndTrackMachine(MachineType.QUALITY_CONTROL);
        qcRobot.operate();

        boolean passed = ((QualityControlRobot) qcRobot).isPassed();
        if (passed) {
            WorkFlowRobot packer = (WorkFlowRobot) createAndTrackMachine(MachineType.PACKAGING);
            packer.operate();
            System.out.println(" Laptop passed QC and was packaged.");
        } else {
            System.out.println(" Laptop failed QC.");
        }

        return new ProductionResult(passed);
    }

    private List<Machine> initializeToolMachines() {
        ChooseDecorator decoratorHandler = new ChooseDecorator();

        Machine[] toolMachines = {
                factory.createMachine(MachineType.CUTTING),
                factory.createMachine(MachineType.DRILLING),
                factory.createMachine(MachineType.WELDING)
        };

        for (int i = 0; i < toolMachines.length; i++) {
            Machine machine = toolMachines[i];
            machine.addObserver(engineer);

            if (machine instanceof ToolMachine) {
                machine = decoratorHandler.applyDecoratorsDynamically((ToolMachine) machine, decorators);
            }

            allMachines.add(machine);
            toolMachines[i] = machine;
        }

        return List.of(toolMachines);
    }

    private boolean runToolMachineWorkflow(List<Machine> toolMachines) {
        for (Machine machine : toolMachines) {
            machine.operate();
            if (machine.getState().toString().equalsIgnoreCase("Error")) {
                System.out.println(" Production halted due to malfunction in machine: " + machine.getMachineId());
                return false;
            }
        }
        return true;
    }

    private Machine createAndTrackMachine(MachineType type) {
        Machine machine = factory.createMachine(type);
        machine.addObserver(engineer);
        allMachines.add(machine);
        return machine;
    }

    public List<Machine> getAllMachines() {
        return allMachines;
    }
}
