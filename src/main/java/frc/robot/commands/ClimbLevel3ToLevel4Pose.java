/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.WaitCommand;

import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Pose to climb from Level 3 to Level 4.
 * <p>
 * Assumes that the hanging hook is set and the robot is on the
 * Level 3 bar.
 */
public class ClimbLevel3ToLevel4Pose extends ClimbBasePose {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ClimbLevel3ToLevel4Pose.class.getName());

    public ClimbLevel3ToLevel4Pose() {
        super();
        logger.info("constructing {} for {}", getName());

        // TODO: Add the command groups and commands that make this up
        addCommands();

        logger.info("constructed");
    }

    public ClimbLevel3ToLevel4Pose(double delay) {
        super();
        logger.info("constructing {} stub for {}", getName(), delay);

        addCommands(new WaitCommand(delay));

        logger.info("constructed");
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        csm.endCurrentStep(interrupted);
        if (!interrupted) {
            SmartDashboard.putBoolean(TelemetryNames.Climber.level4Climbed, true);

            csm.doNextStep();
        }
    }

}
