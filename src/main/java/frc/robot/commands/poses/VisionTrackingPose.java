/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.poses;


import frc.robot.Robot;
import frc.robot.commands.PKCommandBase;
import frc.robot.sensors.vision.IVisionSensor;
import frc.robot.sensors.vision.VisionFactory;

import riolog.PKLogger;
import riolog.RioLogger;


public class VisionTrackingPose extends PKCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(VisionTrackingPose.class.getName());

    private final IVisionSensor vision;
    
    public VisionTrackingPose() {
        logger.info("constructing {}", getName());

        vision = VisionFactory.getInstance();

         logger.info("constructed {}", getName());
    }

    @Override
    public void execute() {
        super.execute();

        double y = vision.getY();

         double speed = 0.48;
        if (y >= 7.1) {
            speed = 0.48;
        } else if (y >= 3.1) {
            speed = 0.49;
        } else {
            speed = 0.5;
        }
        Robot.shooterSetSpeed = speed;
    }

}
