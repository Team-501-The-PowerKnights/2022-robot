/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.sensors.elevator;

import riolog.PKLogger;
import riolog.RioLogger;

/**
 * Provides implementation of <code>IElevatorSensor</code> for the
 * <i>Zester-Bot</i>.
 */
class ZesterElevatorSensor extends StubElevatorSensor {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ZesterElevatorSensor.class.getName());

    ZesterElevatorSensor() {
        logger.info("constructing");

        logger.info("constructed");
    }
}
