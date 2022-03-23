/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj2.command.WaitCommand;

import frc.robot.commands.climber.ClimberExtendLevel2;
import frc.robot.commands.drive.DriveBackwardTimed;
import frc.robot.commands.drive.DriveStraightJoystickControl;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Pose to position the robot for climbing on level 2.
 * <p>
 * Drives from an assumed starting point of the wall to a position
 * lined up with bar, and extends the climber at the same time.
 */
public class ClimbPositionForLevel2Pose extends ClimbBasePose {
        
    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ClimbPositionForLevel2Pose.class.getName());

    /**
     * Creates an instance of class to position the robot to
     * climb on Level 2 bar.
     * 
     * @param delay - how long to wait before starting (seconds)
     */
    public ClimbPositionForLevel2Pose(double delay) {
        super();
        logger.info("constucting {} for {}", getName(), delay);

        addCommands(new WaitCommand(delay),
                    new PKParallelCommandGroup(
                        new DriveBackwardTimed(2.0, 0.20),
                        new ClimberExtendLevel2()
                        )
        );

        logger.info("constructed");
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);
        
        // new DriveStraightJoystickControl()

        csm.endCurrentStep(interrupted);
        if (!interrupted) {
            csm.doNextStep();
        }
    }

}
