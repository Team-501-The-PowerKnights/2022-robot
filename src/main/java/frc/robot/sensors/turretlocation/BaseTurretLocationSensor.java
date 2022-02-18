/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.sensors.turretlocation;

import org.slf4j.Logger;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

abstract class BaseTurretLocationSensor implements ITurretLocationSensor {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(BaseTurretLocationSensor.class.getName());

    /** Our sensor's name **/
    protected static final String myName = TelemetryNames.TurretLocation.name;

    BaseTurretLocationSensor() {
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putBoolean(TelemetryNames.TurretLocation.isFound, get());
    }

}
