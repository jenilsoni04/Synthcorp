package org.example.InventorySystem;

public class RawMaterial {
    private int id;
    private String name;
    private int quantity;
    private int min_quantity_req;

    public RawMaterial(String name, int quantity, int min_quantity_req) {
        this.name = name;
        this.quantity = quantity;
        this.min_quantity_req = min_quantity_req;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getMin_quantity_req() {
        return min_quantity_req;
    }

    public void setMin_quantity_req(int min_quantity_req) {
        this.min_quantity_req = min_quantity_req;
    }
}
