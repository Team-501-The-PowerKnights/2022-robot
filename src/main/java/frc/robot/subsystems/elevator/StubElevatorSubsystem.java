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

public class StubElevatorSubsystem extends BaseElevatorSubsystem {

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
    public void lift() {
        super.lift();
        // Stub doesn't implement this

    }

    @Override
    public void lower() {
        super.lower();
        // Stub doesn't implement this

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

}
