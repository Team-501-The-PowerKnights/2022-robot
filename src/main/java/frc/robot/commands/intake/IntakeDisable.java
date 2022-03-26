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


public class IntakeDisable extends IntakeCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(IntakeDisable.class.getName());

    public IntakeDisable() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    @Override
    public void execute() {
      super.execute();

      intake.stop();
      intake.setDefaultCommand(new IntakeDoNothing());
    }
    
    @Override
    public boolean isFinished() {
        return true;
    }

}
