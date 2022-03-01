/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.shooter;


import riolog.PKLogger;
import riolog.RioLogger;


class StubShooterSubsystem extends BaseShooterSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(StubShooterSubsystem.class.getName());

    StubShooterSubsystem() {
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        super.updateTelemetry();

        // Nothing extra here
    }

    @Override
    public void validateCalibration() {
        // Nothing here
    }

    @Override
    public void updatePreferences() {
        super.updatePreferences();

        // Nothing extra here
    }

    @Override
    public void disable() {
        // 
    }

    @Override
    public void stop() {
        // Stub doesn't implement this
    }

    @Override
    public void setTargetRpm(double rpm) {
        // Stub doesn't implement this
    }

    @Override
    public void shoot() {
        // Stub doesn't implement this
    }

    @Override
    public void setSpeed(int canID, double speed) {
        // Stub doesn't implement this
    }

    @Override
    public boolean atTargetVelocity() {
        // Stub doesn't implement this
        return false;
    }

    @Override
    public String getActivePosition() {
        // Stub doesn't implement this
        return null;
    }

}
