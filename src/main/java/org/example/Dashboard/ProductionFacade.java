package org.example.Dashboard;

import org.example.Laptop.LaptopPlanBuilder;
import org.example.Laptop.LaptopProductionPlan;
import org.example.MachineSystem.Decorators.ChooseDecorator;
import org.example.MachineSystem.Factory.MachineFactoryImpl;
import org.example.Production.MaterialChecker;
import org.example.ProductionSystem.ProductionMethods.CustomBatchStrategy;
import org.example.ProductionSystem.ProductionMethods.MassProductionStrategy;
import org.example.ProductionSystem.ProductionMethods.OnDemandProductionStrategy;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class ProductionFacade {
    private final MaterialChecker materialChecker = new MaterialChecker();
    private final Scanner sc = new Scanner(System.in);
    private final ChooseDecorator decoratorHandler = new ChooseDecorator();

    public void runProductionMenu() throws SQLException {
        System.out.println("--- Select Production Mode ---");
        System.out.println("1. Strategy-Based Production");
        System.out.println("2. Demand-Based Production");
        System.out.println("Enter your choice: ");
        int choice = Integer.parseInt(sc.nextLine());

        switch (choice) {
            case 1 -> runStrategyBasedMenu();
            case 2 -> runDemandBasedMenu();
            default -> System.out.println("Invalid option.");
        }
    }

    private void runStrategyBasedMenu() throws SQLException {
        customizeLaptopPlanIfNeeded();
        System.out.println();
        System.out.println("--------- Strategy-Based Production ---------");
        System.out.println("1. Batch Production");
        System.out.println("2. Mass Production");
        System.out.println("3. On-Demand Production");
        System.out.print("Enter your strategy: ");
        int strategy = Integer.parseInt(sc.nextLine());

        switch (strategy) {
            case 1 -> handleBatchProduction();
            case 2 -> handleMassProduction();
            case 3 -> handleOnDemandProduction();
            default -> System.out.println("Invalid strategy.");
        }
    }
    private void runDemandBasedMenu() throws SQLException {
        List<String> decorators = decoratorHandler.getUserChosenDecorators(sc);
        customizeLaptopPlanIfNeeded();
        System.out.print("Enter demand level (high/low): ");
        String level = sc.nextLine().trim().toLowerCase();

        int quantity = 0;

        switch (level) {
            case "high":
                quantity = 10;
                break;
            case "low":
                quantity = 5;
                break;
            default:
                System.out.println("Invalid demand level.");
                return;
        }

        System.out.println("Producing " + quantity + " laptops.");

        boolean useLegacy = askLegacyChoice();
        MachineFactoryImpl factory = new MachineFactoryImpl();
        factory.Oldmachine(useLegacy);

        if (materialChecker.canProduceAllLaptops(quantity)) {
            if (level.equals("high")) {
                new MassProductionStrategy(quantity, factory, decorators).produce();
            } else if (level.equals("low")) {
                for (int i = 0; i < quantity; i++) {
                    new OnDemandProductionStrategy(factory, decorators).produce();
                }
            }
        } else {
            System.out.println("Not enough materials to produce " + quantity + " laptops.");
        }
    }

    public void startBatchProduction(int totalBatch, int laptopsPerBatch,List<String> decorators) throws SQLException {
        boolean useLegacy = askLegacyChoice();
        MachineFactoryImpl factory = new MachineFactoryImpl();
        factory.Oldmachine(useLegacy);
        int total = totalBatch * laptopsPerBatch;
        if (materialChecker.canProduceAllLaptops(total)) {
            new CustomBatchStrategy(totalBatch, laptopsPerBatch,factory,decorators).produce();
        } else {
            System.out.println("Not enough materials for batch production.");
        }
    }

    public void startMassProduction(int quantity,List<String> decorators) throws SQLException {
        boolean useLegacy = askLegacyChoice();
        MachineFactoryImpl factory = new MachineFactoryImpl();
        factory.Oldmachine(useLegacy);
        if (materialChecker.canProduceAllLaptops(quantity)) {
            new MassProductionStrategy(quantity,factory,decorators).produce();
        } else {
            System.out.println("Not enough materials for mass production.");
        }
    }

    public void startOnDemandProduction(List<String> decorators) throws SQLException {
        int quantity = 1;

        boolean keepProducing = true;

        boolean useLegacy = askLegacyChoice();

        MachineFactoryImpl factory = new MachineFactoryImpl();
        factory.Oldmachine(useLegacy);

        while (keepProducing) {
            if (materialChecker.canProduceAllLaptops(quantity)) {
                new OnDemandProductionStrategy(factory,decorators).produce();
            } else {
                System.out.println("Not enough materials for on-demand production.");
            }

            System.out.print("Do you want to produce another laptop? (yes/no): ");
            String response = sc.nextLine();
            keepProducing = response.equalsIgnoreCase("yes");
        }
    }

    private void handleBatchProduction() throws SQLException {
        List<String> decorators = decoratorHandler.getUserChosenDecorators(sc);
        System.out.print("Enter total batch: ");
        int totalBatch = Integer.parseInt(sc.nextLine());
        System.out.print("Enter number of laptops per batch: ");
        int perBatch = Integer.parseInt(sc.nextLine());
        startBatchProduction(totalBatch, perBatch, decorators);
    }

    private void handleMassProduction() throws SQLException {
        List<String> decorators = decoratorHandler.getUserChosenDecorators(sc);
        System.out.print("Enter quantity: ");
        int quantity = Integer.parseInt(sc.nextLine());
        startMassProduction(quantity, decorators);
    }

    private void handleOnDemandProduction() throws SQLException {
        List<String> decorators = decoratorHandler.getUserChosenDecorators(sc);
        startOnDemandProduction(decorators);
    }
    private boolean askLegacyChoice() {
        System.out.print("Do you want to use legacy machines? (yes/no): ");
        String choice = sc.nextLine().trim();
        return choice.equalsIgnoreCase("yes");
    }
    private void customizeLaptopPlanIfNeeded() {
        System.out.print("Do you want to add extra components (e.g., lighting keyboard, fingerprint)? (yes/no): ");
        String response = sc.nextLine().trim();

        if (response.equalsIgnoreCase("yes")) {
            LaptopPlanBuilder builder = new LaptopPlanBuilder();

            for (String material : LaptopProductionPlan.getOptionalComponentOptions()) {
                System.out.print("Include " + material + "? (yes/no): ");
                String include = sc.nextLine().trim();
                if (include.equalsIgnoreCase("yes")) {
                    System.out.print("Quantity per laptop for " + material + ": ");
                    int quantity = Integer.parseInt(sc.nextLine());
                    builder.addOptionalMaterial(material, quantity);
                }
            }

            LaptopProductionPlan.applyOptionalMaterials(builder.build());
            System.out.println("Optional components added to production plan.");
        } else {
            System.out.println("Using base laptop components only.");
        }
    }

}
