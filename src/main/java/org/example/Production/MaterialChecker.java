package org.example.Production;

import org.example.InventorySystem.InventoryConnector;
import org.example.InventorySystem.InventorySystemFacade;
import org.example.Laptop.LaptopProductionPlan;

public class MaterialChecker {

    public static boolean canProduceAllLaptops(int laptopCount) {
        InventorySystemFacade inventory = InventoryConnector.getInventorySystem();
        LaptopProductionPlan.setLaptopQuantity(laptopCount);

        System.out.println();
        System.out.println("Checking materials for " + laptopCount + " laptops...");
        boolean allGood = true;

        for (String material : LaptopProductionPlan.getAllMaterialNames()) {

            int required = LaptopProductionPlan.getQuantityFor(material);


            if (!inventory.hasSufficientMaterial(material, required)) {

                int available = inventory.getMaterialQuantity(material);
                int deficit = required - available;
                System.out.println("Not enough " + material + ". Required: " + required + ", Available: " + available);
                System.out.println("You need to buy " + deficit + " more " + material + ".");
                allGood = false;
            }
        }
        return allGood;
    }
}
