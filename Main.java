package com.company;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Scanner;

import static java.math.BigDecimal.*;

public class Main {
    // Here is the gravitational constant, used in calculating force in the formula F=GMm/r^2
    public static final BigDecimal GRAVITATIONAL_CONSTANT = valueOf(6.6743e-11);
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Welcome. Today we will be demonstrating the affect of gravity on different objects.");
        System.out.println("To what degree of precision would you prefer the calculations be (in terms of decimal places)?");
        int scaleNum = scan.nextInt();
        System.out.println("Please input the approximate mass in kg of the first object without using decimals (use E notation for large values ex. 6.3e7).");
        // This is creating our first interstellarM object. It is in the very center of our calculating universe, and all of the math will revolve around it.
        BigDecimal tempMass = scan.nextBigDecimal();
        BigDecimal tempXAxis;
        BigDecimal tempYAxis;
        InterstellarMaterial mainMat = new InterstellarMaterial(tempMass);
        System.out.println("Thank you. Please now input the number of other objects in this universe.");
        int objectNum = scan.nextInt();
        // This is the array that holds the values of the interstellarM objects. This lines up with the want to hold values in a list or similar in the SkillsUSA Contest Guidelines
        InterstellarMaterial[] interstellarMaterials = new InterstellarMaterial[objectNum];
        for (int i = 0; i < objectNum; i++) {
            System.out.println("Thank you. Please, in this order, for object #" + (i+1) + " print the x value in m, the y value in m, and the mass of the object in kg, with new lines in between each without using decimals (use E notation for large values ex. 6.3e7).");
            tempXAxis = scan.nextBigDecimal();
            tempYAxis = scan.nextBigDecimal();
            tempMass = scan.nextBigDecimal();
            // This stores each object's data proceeding into itself.
            interstellarMaterials[i] = new InterstellarMaterial(tempXAxis, tempYAxis, tempMass);
        }
        NetForce[] forceOf = new NetForce[objectNum];
        for (int i = 0; i < objectNum; i++) {
            forceOf[i] = forceCalc(mainMat, interstellarMaterials[i], scaleNum);
            System.out.println("\nMain and Material #" + i + ".");
            System.out.println("The force that the two objects are being pulled together by is " + forceOf[i].newtonForce + "N.");
            System.out.println("The angle at which the main object is being pulled is " + forceOf[i].angleOfForce + " degrees.");
        }
        NetForce finalForce = finalForceCalc(forceOf, interstellarMaterials, scaleNum);
        System.out.println("\nAll of them together.");
        System.out.println("The force that the main object is being pulled by is " + finalForce.newtonForce + "N.");
        System.out.println("The angle at which the main object is being pulled is about " + finalForce.angleOfForce + " degrees.");
    }

    public static NetForce forceCalc(InterstellarMaterial mainMat, InterstellarMaterial subMat, int scaleNum) {
        MathContext mc = new MathContext(scaleNum,RoundingMode.HALF_UP);
        BigDecimal distanceSquared = (subMat.xAxis.pow(2).add(subMat.yAxis.pow(2)));
        BigDecimal newtonForce = (GRAVITATIONAL_CONSTANT.multiply(mainMat.objectMass)).multiply(subMat.objectMass).divide(distanceSquared, mc);
        double angleOfForce;
        if (!(subMat.yAxis.equals(0))) { angleOfForce =  (Math.round(Math.toDegrees(Math.atan(subMat.xAxis.doubleValue()/subMat.yAxis.doubleValue()))*(Math.pow(10, scaleNum))))/(Math.pow(10, scaleNum)); }
        else { angleOfForce = 0; }
        return new NetForce(newtonForce, angleOfForce);
    }

    public static NetForce finalForceCalc(NetForce[] forceOf, InterstellarMaterial[] inMats, int scaleNum) {
        MathContext mc = new MathContext(scaleNum,RoundingMode.HALF_UP);
        BigDecimal newtonForceX = valueOf(0);
        BigDecimal newtonForceY = valueOf(0);
        for (int i = 0; i < forceOf.length; i++) {
            newtonForceX = newtonForceX.add(forceOf[i].newtonForce.multiply(valueOf(Math.cos((forceOf[i].angleOfForce*Math.PI)/180.0))));
            newtonForceY = newtonForceY.add(forceOf[i].newtonForce.multiply(valueOf(Math.sin((forceOf[i].angleOfForce*Math.PI)/180.0))));
        }
        BigDecimal newtonForce = newtonForceX.pow(2).add(newtonForceY.pow(2)).sqrt(mc);
        double angleOfForce = 0;
        if (!(0 == newtonForceY.doubleValue())){angleOfForce = Math.round(Math.toDegrees(Math.atan((newtonForceY.divide(newtonForceX, 20, RoundingMode.HALF_UP)).doubleValue()))*(Math.pow(10, scaleNum))/(Math.pow(10, scaleNum)));}
        return new NetForce(newtonForce, angleOfForce);
    }
}
