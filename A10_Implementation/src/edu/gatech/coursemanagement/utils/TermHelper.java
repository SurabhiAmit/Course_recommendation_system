package edu.gatech.coursemanagement.utils;

import edu.gatech.coursemanagement.entity.Term;

public class TermHelper {
    public static Term getTerm(long value) {
        if (value % 4 == 0)
            return Term.FALL;

        if (value % 4 == 1)
            return Term.WINTER;

        if (value % 4 == 2)
            return Term.SPRING;

        if (value % 4 == 3)
            return Term.SUMMER;

        return Term.FALL;
    }

    public static int getYear(long value) {
        if (value == 0)
            return 2017;

        if (value >= 1 && value <= 4)
            return 2018;

        if (value >= 5 && value <= 8)
            return 2019;

        if (value >= 9 && value <= 12)
            return 2020;

        if (value >= 13 && value <= 16)
            return 2021;

        if (value >= 17 && value <= 20)
            return 2022;

        if (value >= 21 && value <= 24)
            return 2023;

        if (value >= 25 && value <= 28)
            return 2024;

        if (value >= 29 && value <= 32)
            return 2025;

        return 2017;
    }
}
