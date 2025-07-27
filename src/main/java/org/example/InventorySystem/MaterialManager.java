package org.example.InventorySystem;

public interface MaterialManager {
    void addmaterial(RawMaterial material);
    void updateMaterial(String name, int newQuantity, int newMin);
    void deleteMaterial(String name);
    void useMaterial(String name, int amount);
}
