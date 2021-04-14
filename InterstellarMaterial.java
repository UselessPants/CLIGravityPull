package com.company;


import java.math.BigDecimal;

public class InterstellarMaterial {
    BigDecimal xAxis;
    BigDecimal yAxis;
    BigDecimal objectMass;
    public InterstellarMaterial(BigDecimal xAxis, BigDecimal yAxis, BigDecimal objectMass) {
        this.objectMass = objectMass;
        this.yAxis = yAxis;
        this.xAxis = xAxis;
    }
    public InterstellarMaterial(BigDecimal objectMass) {
        this.objectMass = objectMass;
        this.xAxis = BigDecimal.valueOf(0);
        this.yAxis = BigDecimal.valueOf(0);
    }
    public BigDecimal returnXAxis() {
        return xAxis;
    }
    public BigDecimal returnYAxis() {
        return yAxis;
    }
    public BigDecimal returnObjectMass() {
        return objectMass;
    }
}
