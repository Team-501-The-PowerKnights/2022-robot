/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.turret;


import riolog.PKLogger;
import riolog.RioLogger;


class StubTurretSubsystem extends BaseTurretSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(StubTurretSubsystem.class.getName());

    StubTurretSubsystem() {
        logger.info("constructing");

        logger.info("constructed");
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
    public void setTurretAngle(double angle) {
        // Stub doesn't implement this
    }

    @Override
    public void home() {
        // Stub doesn't implement this
    }

    @Override
    public void setSpeed(int canID, double speed) {
        // Stub doesn't implement this
    }

    @Override
    public void jogCW() {
        // Stub doesn't implement this
    }

    @Override
    public void jogCCW() {
        // Stub doesn't implement this
    }

    @Override
    public void setAngleFromVision() {
        // Stub doesn't implement this
    }

    @Override
    public void holdAngle() {
        // Stub doesn't implement this
    }

    @Override
    public boolean isAtAngle(double targetAngle) {
        // Stub doesn't implement this
        return false;
    }

}
