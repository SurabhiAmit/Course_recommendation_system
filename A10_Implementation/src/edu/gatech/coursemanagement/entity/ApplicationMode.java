package edu.gatech.coursemanagement.entity;

/**
 * CS6310 - Team 45
 * edu.gatech.coursemanagement.entity.ApplicationMode.java
 */

public enum ApplicationMode {
    INITIAL_MODE(1),
    RESUME_MODE(2);

	int value;
	
	ApplicationMode(int value)
    {
        this.value = value;
    }
	
    // TODO: Determine which mode is active at any given time
	
    public int getValue(){
    	return this.value;
    }

    public static ApplicationMode getFromInt(int value) {
        if (value == 1)
            return ApplicationMode.INITIAL_MODE;
        if (value == 2)
            return ApplicationMode.RESUME_MODE;

        return ApplicationMode.INITIAL_MODE;
    }
}
