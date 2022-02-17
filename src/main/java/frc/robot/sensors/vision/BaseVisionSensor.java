/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.sensors.vision;

import org.slf4j.Logger;

import frc.robot.telemetry.TelemetryNames;

import riolog.RioLogger;

abstract class BaseVisionSensor implements IVisionSensor {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(BaseVisionSensor.class.getName());

    protected static final String myName = TelemetryNames.Vision.name;

    // Flag for whether active
    protected boolean isActive;

    BaseVisionSensor() {
        logger.info("constructing");

        isActive = false;

        logger.info("constructed");
    }

    @Override
    public void enable() {
        isActive = true;
    }

    @Override
    public void disable() {
        isActive = false;
    }

    public boolean isActive() {
        return isActive;
    }

}
