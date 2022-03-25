/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.climber;

import riolog.PKLogger;
import riolog.RioLogger;

class StubClimberSubsystem extends BaseClimberSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(StubClimberSubsystem.class.getName());

    StubClimberSubsystem() {
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        super.updateTelemetry();
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
        super.stop();

        // Stub doesn't implement this
    }

    @Override
    public void run(double speed) {
        // Stub doesn't implement this
    }

    @Override
    public void goToSetPoint(double setPoint) {
        // Stub doesn't implement this
    }

    @Override
    public double getRightPosition() {
        // Stub doesn't implement this
        return 0;
    }

    @Override
    public double getLeftPosition() {
        // Stub doesn't implement this
        return 0;
    }

    @Override
    public double getAveragePosition() {
        // Stub doesn't implement this
        return 0;
    }

    @Override
    public void zeroPosition() {
        // Stub doesn't implement this
    }

}
