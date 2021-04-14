package com.company;


import java.math.BigDecimal;

public class NetForce {
    BigDecimal newtonForce;
    double angleOfForce;
    double angleOfForceRadians = ((angleOfForce*Math.PI)/180.0);
    public NetForce(BigDecimal newtonForce, double angleOfForce) {
        this.angleOfForce = angleOfForce;
        this.newtonForce = newtonForce;
    }


    public BigDecimal returnNewtonForce() {
        return newtonForce;
    }
    public double returnAngleOfForce() {
        return angleOfForce;
    }
    public double getAngleOfForceRadians() { return (angleOfForce*Math.PI)/180; }
}
