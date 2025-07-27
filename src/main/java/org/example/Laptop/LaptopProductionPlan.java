package org.example.Laptop;

import java.util.*;

public class LaptopProductionPlan {
    private static int laptopQuantity = 1;

    private static final Map<String, Integer> defaultMaterials = new HashMap<>();

    private static final Map<String, Integer> optionalMaterials = new HashMap<>();

    static {
        defaultMaterials.put("Motherboard", 1);
        defaultMaterials.put("Screen", 1);
        defaultMaterials.put("RAM", 1);
        defaultMaterials.put("Keyboard", 1);
        defaultMaterials.put("Switch", 1);
        defaultMaterials.put("Camera", 1);
        defaultMaterials.put("Box", 1);
        defaultMaterials.put("Aluminum", 5);
    }

    public static void setLaptopQuantity(int quantity) {
        laptopQuantity = quantity;
    }

    public static int getLaptopQuantity() {
        return laptopQuantity;
    }

    public static int getQuantityFor(String material) {
        if (defaultMaterials.containsKey(material)) {
            return defaultMaterials.get(material) * laptopQuantity;
        }
        return optionalMaterials.getOrDefault(material, 0) * laptopQuantity;
    }

    public static Set<String> getAllMaterialNames() {
        Set<String> all = new HashSet<>(defaultMaterials.keySet());
        all.addAll(optionalMaterials.keySet());
        return all;
    }

    public static void applyOptionalMaterials(Map<String, Integer> extras) {
        optionalMaterials.clear();
        optionalMaterials.putAll(extras);
    }

    public static Map<String, Integer> getDefaultPlan() {
        return Collections.unmodifiableMap(defaultMaterials);
    }

    public static Set<String> getOptionalComponentOptions() {
        return Set.of("Lighting Keyboard", "Mouse", "Fingerprint Sensor", "Stylus Pen", "Touchpad");
    }
}
