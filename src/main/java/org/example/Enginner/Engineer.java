package org.example.Enginner;

public class Engineer {
    private String name;

    public Engineer(String name) {
        this.name = name;
    }

    public void onMachineError(String machineId, String message) {
        System.out.println("Engineer " + name + " notified: " + message + " [Machine: " + machineId + "]");
    }
}
