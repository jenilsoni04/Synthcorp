package org.example;

import org.example.Dashboard.InventoryFacade;
import org.example.Dashboard.ProductionFacade;
import org.example.Production.ProductionPerformance;
import org.example.Dashboard.WareHouseSystemFacade;

import java.sql.SQLException;
import java.util.Scanner;

public class SynthcorpFactory {
    private static final Scanner sc = new Scanner(System.in);
    private static final ProductionFacade productionFacade = new ProductionFacade();
    private static final InventoryFacade inventoryFacade = new InventoryFacade();
    private static final WareHouseSystemFacade warehousefacade = new WareHouseSystemFacade();

    public static void main(String[] args) throws SQLException {
        boolean run = true;

        while (run) {
            printMainMenu();
            int command = getUserInput();

            switch (command) {
                case 1 -> productionFacade.runProductionMenu();
                case 2 -> warehousefacade.WareHouseMenu();
                case 3 -> inventoryFacade.runInventoryMenu();
                case 4 -> handlePerformanceReport();
                case 5 -> {
                    System.out.println("Exiting system. Goodbye!");
                    run = false;
                }
                default -> System.out.println("Invalid command. Try again.");
            }
        }
    }

    private static int getUserInput() {
        while (!sc.hasNextInt()) {
            System.out.print("Please enter a valid number: ");
            sc.nextLine();
        }
        int input = sc.nextInt();
        sc.nextLine();
        return input;
    }

    private static void handlePerformanceReport() {
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = sc.nextLine();
        showProductionPerformance(date);
    }

    private static void showProductionPerformance(String date) {
        ProductionPerformance performance = new ProductionPerformance();
        performance.loadDataForDate(date);

        System.out.println("\n--- Production Performance Report for " + date + " ---");
        System.out.println("Total Laptops Produced : " + performance.getTotalProduced());
        System.out.println("Defective Laptops       : " + performance.getTotalDefective());
        System.out.printf("Success Rate            : %.2f%%\n", performance.getSuccessPercentage());
        System.out.printf("Defective Rate          : %.2f%%\n", performance.getDefectivePercentage());
    }

    private static void printMainMenu() {
        System.out.println("\n===== SynthCorp Terminal =====");
        System.out.println("1. Start Production");
        System.out.println("2. View laptops in warehouse");
        System.out.println("3. Manage Inventory");
        System.out.println("4. View Production Performance");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }
}