/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/
package frc.robot.sensors.gyro;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.sensors.BaseSensor;
import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;


abstract class BaseGyroSensor extends BaseSensor implements IGyroSensor {

    /* Our classes logger */
    private static final PKLogger logger = RioLogger.getLogger(BaseGyroSensor.class.getName());

    protected final boolean gyroReversed;

    BaseGyroSensor() {
        super(TelemetryNames.Gyro.name);
        logger.info("constructing");

        // FIXME - Get from Properties file
        this.gyroReversed = false;

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putNumber(TelemetryNames.Gyro.roll, getRoll());
        SmartDashboard.putNumber(TelemetryNames.Gyro.pitch, getPitch());
        SmartDashboard.putNumber(TelemetryNames.Gyro.yaw, getYaw());

        SmartDashboard.putNumber(TelemetryNames.Gyro.angle, getAngle());
        SmartDashboard.putNumber(TelemetryNames.Gyro.heading, getHeading());
    }

    @Override
    public double getHeading() {
        return Math.IEEEremainder(getAngle(), 360) * (gyroReversed ? -1 : 1);
    }

}
