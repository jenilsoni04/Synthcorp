package org.example.Production;

public class ProductionResult {
    private final boolean passedQC;

    public ProductionResult(boolean passedQC) {
        this.passedQC = passedQC;
    }

    public boolean isPassedQC() {
        return passedQC;
    }
}
