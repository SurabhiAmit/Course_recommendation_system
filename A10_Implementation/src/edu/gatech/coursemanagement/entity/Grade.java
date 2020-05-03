package edu.gatech.coursemanagement.entity;

/**
 * CS6310 - Team 45
 * edu.gatech.coursemanagement.entity.Grade.java
 */

public enum Grade {
    A,
    B,
    C,
    D,
    F;

    public static Grade parse(String value) {
        if (value.equals("A"))
            return Grade.A;
        if (value.equals("B"))
            return Grade.B;
        if (value.equals("C"))
            return Grade.C;
        if (value.equals("D"))
            return Grade.D;
        if (value.equals("F"))
            return Grade.F;

        return Grade.F;
    }

}