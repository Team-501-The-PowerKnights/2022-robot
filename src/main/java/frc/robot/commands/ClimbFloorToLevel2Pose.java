/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands;


import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Pose to climb from floor to Level 2.
 * <p>
 * Assumes that the climber hook is set and the robot is correctly
 * positioned under the bar.
 */
public class ClimbFloorToLevel2Pose {
       
    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ClimbFloorToLevel2Pose.class.getName());

    public ClimbFloorToLevel2Pose() {
        logger.info("constructing");

        logger.info("constructed");
    }

}
