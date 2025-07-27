package org.example.Laptop;


import java.util.HashMap;
import java.util.Map;

public class LaptopPlanBuilder {
    private final Map<String, Integer> optionalMaterials = new HashMap<>();

    public LaptopPlanBuilder addOptionalMaterial(String name, int quantity) {
        optionalMaterials.put(name, quantity);
        return this;
    }

    public Map<String, Integer> build() {
        return optionalMaterials;
    }
}

