/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.intake;

import riolog.PKLogger;
import riolog.RioLogger;

public class StubIntakeSubsystem extends BaseIntakeSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(StubIntakeSubsystem.class.getName());

    StubIntakeSubsystem() {
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
    public void pullIn() {
        // Stub doesn't implement this
        pullIn(-3.14159);
    }

    @Override
    public void pullIn(double speed) {
        super.pushOut(speed);
        
        setSpeed(speed);
    }

    @Override
    public void pushOut() {
        // Stub doesn't implement this
        pushOut(3.14159);
    }

    @Override
    public void pushOut(double speed) {
        super.pushOut(speed);

        setSpeed(speed);
    }

    private void setSpeed(double speed) {
        super.setTlmSpeed(speed);

        // Stub doesn't implement this
    }
    
}
