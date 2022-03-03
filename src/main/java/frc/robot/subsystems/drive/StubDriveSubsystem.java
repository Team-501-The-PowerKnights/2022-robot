/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

import java.util.List;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;

import riolog.PKLogger;
import riolog.RioLogger;

class StubDriveSubsystem extends BaseDriveSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(StubDriveSubsystem.class.getName());

    StubDriveSubsystem() {
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void updateTelemetry() {
        super.updateTelemetry();
        // Stub doesn't implement this
    }

    @Override
    public void validateCalibration() {
        // Stub doesn't implement this
    }

    @Override
    public void updatePreferences() {
        super.updatePreferences();
        // Stub doesn't implement this
    }

    @Override
    public void disable() {
        // Stub doesn't implement this
    }

    @Override
    public void stop() {
        // Stub doesn't implement this
    }

    @Override
    public void setBrake(boolean brakeOn) {
        // Stub doesn't implement this
    }

    @Override
    public void drive(double hmiSpeed, double hmiTurn) {
        // Stub doesn't implement this
    }

    @Override
    public void followPath(Pose2d start, List<Translation2d> interiorWaypoints, Pose2d end) {
        // Stub doesn't implement this
    }

    @Override
    public void setSpeed(int canID, double speed) {
        // Stub doesn't implement this
    }

    @Override
    public void swap() {
        // Stub doesn't implement this
    }

    @Override
    public double getEncoderClicks() {
        // Stub doesn't implement this
        return 0;
    }

    @Override
    public double getEncoderVelocity() {
        // Stub doesn't implement this
        return 0;
    }

    @Override
    public double convertInchesToEncoderClicks(double inches) {
        // Stub doesn't implement this
        return 0;
    }

}
