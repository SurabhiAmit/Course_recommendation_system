package edu.gatech.coursemanagement.entity;

/**
 * CS6310 - Team 45
 * edu.gatech.coursemanagement.entity.Term.java
 */

public enum Term {
    WINTER(1),
    SPRING(2),
    SUMMER(3),
    FALL(4);

    int value;

    Term(int value)
    {
        this.value = value;
    }

    public static Term parse(String value) {
        if (value == "1")
            return Term.WINTER;
        if (value == "2")
            return Term.SPRING;
        if (value == "3")
            return Term.SUMMER;
        if (value == "4")
            return Term.FALL;

        return Term.WINTER;
    }

    public static Term getFromInt(int value) {
        if (value == 1)
            return Term.WINTER;
        if (value == 2)
            return Term.SPRING;
        if (value == 3)
            return Term.SUMMER;
        if (value == 4)
            return Term.FALL;

        return Term.WINTER;
    }
    
      public int getValue(){
    	return this.value;
    }


}
