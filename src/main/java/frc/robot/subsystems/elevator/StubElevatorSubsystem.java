/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.elevator;

import riolog.PKLogger;
import riolog.RioLogger;

class StubElevatorSubsystem extends BaseElevatorSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(StubElevatorSubsystem.class.getName());

    StubElevatorSubsystem() {
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

        setSpeed(0.0);
    }

    @Override
    public void lift() {
        super.lift();

        // Stub doesn't implement this
        setSpeed(3.14159);
    }

    @Override
    public void lower() {
        super.lower();

        // Stub doesn't implement this
        setSpeed(-3.14159);
    }

    @Override
    public boolean isFull() {
        // Stub doesn't implement this
        return false;
    }

    @Override
    public void liftToLimit() {
        super.liftToLimit();
        // Stub doesn't implement this
    }

    private void setSpeed(double speed) {
        setTlmSpeed(speed);

        // Stub doesn't implement this
    }

}
