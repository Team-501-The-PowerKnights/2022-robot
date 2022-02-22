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

    /**
     * Creates a new DriveJoystickControl.
     */
    public ElevatorManualControl() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        super.execute();

        double liftSpeed = oi.getIntakeSpeed();
        if (liftSpeed == 0) {
            elevator.stop();
        } else if (liftSpeed > 0) {
            elevator.lift();
        }

        double incrementSpeed = oi.getElevatorSpeed();
        if (incrementSpeed == 0) {
            elevator.stopIncrement();
        } else if (incrementSpeed > 0) {
            elevator.increment();
        }
    }
}
