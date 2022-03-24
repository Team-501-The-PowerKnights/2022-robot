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
 * Pose to climb from floor to Level 2.
 * <p>
 * Assumes that the climber hook is set and the robot is correctly
 * positioned under the bar.
 */
public class ClimbFloorToLevel2Pose extends ClimbBasePose {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ClimbFloorToLevel2Pose.class.getName());

    public ClimbFloorToLevel2Pose() {
        super();
        logger.info("constructing {}", getName());

        // TODO: Add the command groups and commands that make this up
        addCommands();

        logger.info("constructed");
    }

    public ClimbFloorToLevel2Pose(double delay) {
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
            SmartDashboard.putBoolean(TelemetryNames.Climber.level2Climbed, true);

            csm.doNextStep();
        }
    }

}
