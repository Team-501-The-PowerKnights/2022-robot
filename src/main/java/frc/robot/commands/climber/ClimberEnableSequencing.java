/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.climber;

import riolog.PKLogger;
import riolog.RioLogger;

public class ClimberEnableSequencing extends ClimberOICommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ClimberEnableSequencing.class.getName());

    public ClimberEnableSequencing() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    public boolean isFinished() {
        return (oi.getDriverClimberStart() && oi.getOperatorClimberStart());
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        
        // Zero encoder position in advance of climber sequencing; they should never be
        // zeroed after this point
        climber.zeroPosition();
        ClimberStateMachine.getInstance().beginClimberSequencing();
    }

}
