/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.turret;


import frc.robot.sensors.vision.IVisionSensor;
import frc.robot.sensors.vision.VisionFactory;

import riolog.PKLogger;
import riolog.RioLogger;


public class TurretVisionAlign extends TurretCommandBase {
    
    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(TurretVisionAlign.class.getName());

    private IVisionSensor vision;

    public TurretVisionAlign() {
        logger.info("constructing {}", getName());

        vision = VisionFactory.getInstance();

        logger.info("constructed");
    }

    @Override
    public void initialize() {
        super.initialize();

        vision.enable();
        turret.initVisionTracking();
    }

    @Override
    public void execute() {
        super.execute();

        turret.setAngleFromVision();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        vision.disable();
        turret.holdAngle();
    }

}
