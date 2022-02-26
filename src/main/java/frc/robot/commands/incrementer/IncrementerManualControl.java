/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.incrementer;

import riolog.PKLogger;
import riolog.RioLogger;

public class IncrementerManualControl extends IncrementerOICommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(IncrementerManualControl.class.getName());

    /**
     * Creates a new DriveJoystickControl.
     */
    public IncrementerManualControl() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        super.execute();

        double incrementSpeed = oi.getIntakeSpeed();
        if (incrementSpeed == 0) {
            incrementer.stop();
        } else if (incrementSpeed > 0) {
            incrementer.increment();
        }
    }
}
