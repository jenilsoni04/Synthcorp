package org.example.InventorySystem;

public class InventorySystemFacade {
    private final MaterialManager manager;
    private final MaterialQuery query;

    public InventorySystemFacade(MaterialManager manager, MaterialQuery query) {
        this.manager = manager;
        this.query = query;
    }
    public void addMaterial(RawMaterial material)
    {
        manager.addmaterial(material);
    }
    public void useMaterial(String name, int qty) {
        manager.useMaterial(name, qty);
    }
    public void updateMaterial(String name, int newQuantity, int newMin)
    {
        manager.updateMaterial(name,newQuantity,newMin);
    }
    public void deleteMaterial(String name)
    {
        manager.deleteMaterial(name);
    }
    public void displayInventory() {
        query.displayMaterials();
    }
    public boolean hasSufficientMaterial(String material, int requiredQty)
    {
       return query.hasSufficientMaterial(material,requiredQty);

    }
    public int getMaterialQuantity(String material)
    {
        return query.getMaterialQuantity(material);

    }

}
