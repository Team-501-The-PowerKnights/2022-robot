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
import frc.robot.commands.drive.DriveJoystickControl;
import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Pose to position the robot for hanging on level 2.
 * <p>
 * Drives from an assumed starting point of the wall to a position
 * lined up with bar, and extends the climber at the same time.
 */
public class ClimberPositionForLevel2Pose extends PKSequentialCommandGroup {
        
    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ClimberPositionForLevel2Pose.class.getName());

    // Duration to hold before starting to drive and extend
    private final double delay;

    public ClimberPositionForLevel2Pose(double delay) {
        logger.info("constucting {} for {}", getName(), delay);

        this.delay = delay;

        addCommands(new WaitCommand(delay),
                    new PKParallelCommandGroup(new DriveBackwardTimed(3.0),
                                               new ClimberExtendLevel2()),
                    new DriveJoystickControl()
                    );

        logger.info("constructed");
    }

}
