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


/**
 * Command to extend the Intake subystem.
 * <p>
 * The command runs as a one shot, as the pneumatics don't require
 * anything other than setting the state.
 */
public class IntakeExtend extends IntakeCommandBase {
    
    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(IntakeExtend.class.getName());

    public IntakeExtend() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    @Override
    public void execute() {
        super.execute();

        intake.extend();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
