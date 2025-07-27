package org.example.Dashboard;

import org.example.InventorySystem.InventoryConnector;
import org.example.InventorySystem.InventorySystemFacade;
import org.example.InventorySystem.RawMaterial;

import java.util.Scanner;

public class InventoryFacade {
    private final InventorySystemFacade inventorySystem = InventoryConnector.getInventorySystem();
    private final Scanner sc = new Scanner(System.in);

    public void runInventoryMenu() {
        boolean menu = true;
        while (menu) {
            printMenu();
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> inventorySystem.displayInventory();
                case 2 -> addItem();
                case 3 -> deleteItem();
                case 4 -> updateItem();
                case 5 -> menu = false;
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void printMenu() {
        System.out.println();
        System.out.println("--- Inventory Menu ---");
        System.out.println("1. View Inventory");
        System.out.println("2. Add Item");
        System.out.println("3. Delete Item");
        System.out.println("4. Update Item");
        System.out.println("5. Return to Main Menu");
        System.out.print("Enter your choice: ");
    }

    private void addItem() {
        System.out.print("Enter item name: ");
        String name = sc.nextLine();
        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();
        System.out.print("Enter minimum requirement: ");
        int min = sc.nextInt();
        sc.nextLine();

        RawMaterial mat = new RawMaterial(name, qty, min);
        inventorySystem.addMaterial(mat);
        System.out.println("Item added.");
    }

    private void deleteItem() {
        System.out.print("Enter item name to delete: ");
        String name = sc.nextLine();
        inventorySystem.deleteMaterial(name);
        System.out.println("Item deleted.");
    }

    private void updateItem() {
        System.out.print("Enter item name to update: ");
        String name = sc.nextLine();
        System.out.print("Enter new quantity: ");
        int qty = sc.nextInt();
        System.out.print("Enter new minimum requirement: ");
        int min = sc.nextInt();
        sc.nextLine();

        inventorySystem.updateMaterial(name, qty, min);
        System.out.println("Item updated.");
    }
}
