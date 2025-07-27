package org.example.MachineSystem.Decorators;

import org.example.MachineSystem.MainMachines.ToolMachine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ChooseDecorator {

    public List<String> getUserChosenDecorators(Scanner sc) {
        List<String> decorators = new ArrayList<>();

        System.out.print("Apply Energy Efficient Mode? (y/n): ");
        if (sc.nextLine().trim().equalsIgnoreCase("y")) {
            decorators.add("energy");
        }

        System.out.print("Apply Error Detection Mode? (y/n): ");
        if (sc.nextLine().trim().equalsIgnoreCase("y")) {
            decorators.add("error");
        }

        return decorators;
    }

    public ToolMachine applyDecoratorsDynamically(ToolMachine baseMachine, List<String> decorators) {
        ToolMachine decorated = baseMachine;

        for (String decorator : decorators) {
            if (decorator.equalsIgnoreCase("energy")) {
                decorated = new EnergyEfficientModeDecorator(decorated);
            } else if (decorator.equalsIgnoreCase("error")) {
                decorated = new ErrorDetectionDecorator(decorated);
            }
        }

        return decorated;
    }

}
