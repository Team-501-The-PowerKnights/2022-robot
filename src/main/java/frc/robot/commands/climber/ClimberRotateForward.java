/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.climber;

import frc.robot.commands.PKCommandBase;
import frc.robot.subsystems.climber.ClimberFactory;
import frc.robot.subsystems.climber.IClimberSubsystem;

import riolog.PKLogger;
import riolog.RioLogger;


// TODO: Can't be a Climber subsystem as need to run two (need a Pose?)
public class ClimberRotateForward extends PKCommandBase {
    
    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ClimberRotateForward.class.getName());

    // Handle to our subsystem
    protected final IClimberSubsystem climber;

    public ClimberRotateForward() {
        logger.info("constructing {}", getName());

        climber = ClimberFactory.getInstance();
        // Do not add requirement for climber

        logger.info("constructed");
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    protected void firstExecution() {
        logger.trace("climber.rotateForward() called in firstExecution()");
        climber.rotateForward();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        logger.trace("climber.rotateBackward() called in end()");
        climber.rotateBackward();
    }

}
