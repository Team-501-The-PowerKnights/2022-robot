/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.sensors.chassis;


import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Provides implementation of <code>IWallDistanceSensor</code> which has no sensor
 * or other useful functionality; but which won't blow up if instantiated and
 * 'used'.
 */
public class StubWallDistanceSensor extends BaseWallDistanceSensor {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(StubWallDistanceSensor.class.getName());

    StubWallDistanceSensor() {
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public double get() {
        // Stub doesn't implement this - returns false
        return 0.0;
    }

    @Override
    public boolean isValid() {
        // Stub doesn't implement this - returns false
        return false;
    }

}
