/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.climber;


// import frc.robot.OI;
// import frc.robot.Robot;

import riolog.PKLogger;
import riolog.RioLogger;


public class ClimberRetractInPits extends ClimberOICommandBase {
    
    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ClimberRetractInPits.class.getName());

    public ClimberRetractInPits() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    
    // // Called repeatedly when this Command is scheduled to run
    // @Override
    // public void execute() {
    //     super.execute();

    //     // TODO - Can we just call end from here?
    //     if (Robot.isFieldConnected()) {
    //         return;
    //     }

    //     if (OI.getInstance().getClimberRetractEnable()) {
    //         climber.retract();
    //     } else {
    //         climber.stop();
    //     }
    // }

    // // Called repeatedly after execute to determine if command is finished
    // @Override
    // public boolean isFinished() {
    //     return Robot.isFieldConnected();
    // }

    // // Called once when either the Command finishes normally, or when it
    // // is interrupted/canceled.
    // @Override
    // public void end(boolean interrupted) {
    //     super.end(interrupted);

    //     climber.stop();
    // }

}
