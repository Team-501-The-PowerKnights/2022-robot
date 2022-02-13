/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.utils;


import riolog.PKLogger;
import riolog.RioLogger;


/**
 * This class defines a set of statuses which can be used to set telemetry which
 * maps to defined colors in the Dashboard color widget that we extended. This
 * provides something better than a true / false with green / red.
 */
public class PKStatus {

    /* Our classes logger */
    @SuppressWarnings("unused")
    private static final PKLogger logger = RioLogger.getLogger(PKStatus.class.getName());

    public final double tlmValue;

    public final String name;

    public PKStatus(double tlmValue, String name) {
        this.tlmValue = tlmValue;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    /**
     * Maps to Dashboard color widget <i>Black</i>
     */
    public static final PKStatus unknown = new PKStatus(0.0, "Unknown");

    /**
     * Maps to Dashboard color widget <i>Grey</i>
     */
    public static final PKStatus inProgress = new PKStatus(1.0, "In-Progress");

    /**
     * Maps to Dashboard color widget <i>Green</i>
     */
    public static final PKStatus success = new PKStatus(2.0, "Success");

    /**
     * Maps to Dashboard color widget <i>Yellow</i>
     */
    public static final PKStatus degraded = new PKStatus(3.0, "Degraded");

    /**
     * Maps to Dashboard color widget <i>Red</i>
     */
    public static final PKStatus failed = new PKStatus(4.0, "Failure");

}
