/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.sensors.gyro;


import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Provides implementation of <code>IGyroSensor</code> which has no sensor or
 * other useful functionality; but which won't blow up if instantiated and
 * 'used'.
 */
class StubGyroSensor extends BaseGyroSensor {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(StubGyroSensor.class.getName());

    StubGyroSensor() {
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public double getRoll() {
        // Stub doesn't implement this
        return 0;
    }

    @Override
    public double getPitch() {
        // Stub doesn't implement this
        return 0;
    }

    @Override
    public double getYaw() {
        // Stub doesn't implement this
        return 0;
    }

    @Override
    public double getAngle() {
        // Stub doesn't implement this
        return 0;
    }

}
