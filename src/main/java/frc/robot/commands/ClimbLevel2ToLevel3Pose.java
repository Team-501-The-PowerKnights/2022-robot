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
 * Pose to climb from Level 2 to Level 3.
 * <p>
 * Assumes that the hanging hook is set and the robot is on the
 * Level 2 bar.
 */
public class ClimbLevel2ToLevel3Pose {
        
    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ClimbLevel2ToLevel3Pose.class.getName());

    public ClimbLevel2ToLevel3Pose() {
        logger.info("constructing");

        logger.info("constructed");
    }

}
