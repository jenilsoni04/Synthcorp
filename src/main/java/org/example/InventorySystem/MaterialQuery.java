package org.example.InventorySystem;

public interface MaterialQuery {
    void displayMaterials();
    boolean hasSufficientMaterial(String material, int requiredQty);
    int getMaterialQuantity(String material);
}