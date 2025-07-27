package org.example.MachineSystem.WorkFlowRobots;

import org.example.MachineSystem.States.ErrorState;
import org.example.MachineSystem.MainMachines.WorkFlowRobot;
import java.util.Random;

public class QualityControlRobot extends WorkFlowRobot {

    private boolean passed;

    public QualityControlRobot(String machineId) {
        super(machineId);
    }

    @Override
    public void operate() {
        start();
        if (getState() instanceof ErrorState) return;
        System.out.println("Performing quality control...");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        passed = new Random().nextDouble() > 0.3;
        stop();
    }

    public boolean isPassed() {
        return passed;
    }
}
