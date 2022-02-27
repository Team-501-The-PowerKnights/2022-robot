/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.incrementer;

import riolog.PKLogger;
import riolog.RioLogger;

class StubIncrementerSubsystem extends BaseIncrementerSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(StubIncrementerSubsystem.class.getName());

    StubIncrementerSubsystem() {
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
        super.stop();

        setSpeed(0.0);
    }

    @Override
    public void increment() {
        // TODO Auto-generated method stub
    }

    @Override
    public boolean isFull() {
        // Stub doesn't implement this
        return false;
    }

    @Override
    public void incrementToLimit() {
        super.incrementToLimit();
        // Stub doesn't implement this
    }

    private void setSpeed(double speed) {
        setTlmSpeed(speed);

        // Stub doesn't implement this
    }

}