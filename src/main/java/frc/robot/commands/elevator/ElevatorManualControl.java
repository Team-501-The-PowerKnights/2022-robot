/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.elevator;


import riolog.PKLogger;
import riolog.RioLogger;


public class ElevatorManualControl extends ElevatorOICommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ElevatorManualControl.class.getName());

    public ElevatorManualControl() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    private boolean wasEnabled = false;

    @Override
    public void execute() {
        super.execute();

        // FIXME: Continuous calling needs one shot
        double liftSpeed = oi.getIntakeSpeed();
        if (liftSpeed > 0) {
            wasEnabled = true;
            // elevator.liftToLimit();
            elevator.lift();
        } else {
            if (wasEnabled) {
                wasEnabled = false;
                (new ElevatorLiftToFull()).schedule();
            } else {
                elevator.stop();
            }
        }
    }

}
