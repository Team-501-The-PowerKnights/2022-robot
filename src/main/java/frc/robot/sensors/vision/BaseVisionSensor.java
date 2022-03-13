/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.sensors.vision;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.sensors.BaseSensor;
import frc.robot.sensors.SensorNames;
import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;


abstract class BaseVisionSensor extends BaseSensor implements IVisionSensor {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(BaseVisionSensor.class.getName());

    // Flag for whether active
    protected boolean isActive;

    BaseVisionSensor() {
        super(SensorNames.visionName);
        logger.info("constructing");

        isActive = false;

        logger.info("constructed");
    }

    private boolean tlmEnabled = false;
    private boolean tlmActive = false;
    private boolean tlmLocked = false;


    @Override
    public void updateTelemetry() {
        SmartDashboard.putBoolean(TelemetryNames.Vision.enabled, tlmEnabled);
        SmartDashboard.putBoolean(TelemetryNames.Vision.active, tlmActive);
        SmartDashboard.putBoolean(TelemetryNames.Vision.locked, tlmLocked);
    }

    @Override
    public void enable() {
        tlmActive = true;
        tlmEnabled = true;
    }

    @Override
    public void disable() {
        tlmActive = false;
        tlmEnabled = false;
    }

    public boolean isActive() {
        return tlmActive;
    }

    protected void setTlmLocked(boolean locked)
    {
        tlmLocked = locked;
    }
    
}
