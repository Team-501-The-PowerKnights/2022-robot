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
 * Provides implementation of <code>IGyroSensor</code> for the
 * <i>Real-Bot</i> which is based on the navX-MXP sensor.
 */
class GyroSensor extends SuitcaseGyroSensor {

    /* Our classes logger */
    private static final PKLogger logger = RioLogger.getLogger(GyroSensor.class.getName());

    GyroSensor() {
        logger.info("constructing");

        logger.info("constructed");
    }

}
