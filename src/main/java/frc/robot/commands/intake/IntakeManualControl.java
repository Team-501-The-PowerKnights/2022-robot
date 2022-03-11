/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.intake;


import riolog.PKLogger;
import riolog.RioLogger;


public class IntakeManualControl extends IntakeOICommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(IntakeManualControl.class.getName());

    /**
     * Creates a new IntakeManualControl.
     */
    public IntakeManualControl() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        super.execute();

        double speed = oi.getIntakeSpeed();
        if (speed > 0) {
            intake.pullIn();
            intake.extend();
        } else if (speed < 0) {
            intake.pushOut();
            intake.extend();
        } else { // == 0
            intake.stop();
            intake.retract();
        }
    }

}
