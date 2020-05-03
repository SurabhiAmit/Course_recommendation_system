package edu.gatech.coursemanagement.utils;

public class Trace {
    private static boolean _enabled;

    public static void setEnabled(boolean enabled) {
        _enabled = enabled;
    }

    public static void TraceMessage(String message) {
        if (_enabled)
            System.out.println(message);
    }
}
