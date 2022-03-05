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
    public void climb() {
        super.climb();

        // Stub doesn't implement this
    }

    @Override
    public void retract() {
        super.retract();
        // Stub doesn't implement this
    }

    @Override
    public double getPosition() {
        // Stub doesn't implement this
        return 0;
    }

}
