/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.shooter;


import frc.robot.sensors.vision.IVisionSensor;
import frc.robot.sensors.vision.VisionFactory;
import frc.robot.subsystems.drive.DriveFactory;
import frc.robot.subsystems.drive.IDriveSubsystem;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * 
 */
public class ShooterSpinVisionControl extends ShooterCommandBase {
    
    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ShooterSpinVisionControl.class.getName());

    /*
     * We aren't reserving the subsystems because all we are doing
     * is getting values from them (and the sensors).
     */
    // TODO: Make a sensor on shooter for RPMs
    // TODO: Make a sensor for speedometer / odometer on Drive
    private IDriveSubsystem drive;
    private IVisionSensor vision;

    public ShooterSpinVisionControl() {
        logger.info("constructing {}", getName());

        drive = DriveFactory.getInstance();
        vision = VisionFactory.getInstance();

        logger.info("constructed");
    }

    @Override
    public void execute() {
        super.execute();

        double linearVelocity = drive.getEncoderVelocity();
        double coefficient = linearVelocity < 0 ? 0.7 : 0.015;

        double ty = vision.getY();

        // targetRpm = (13.5 * (ty * ty)) - (111.3 * ty) + 3352.4;
        double targetRpm = (1297 + (-15.2 * ty) + (1.25 * (ty * ty))) - (coefficient * linearVelocity);
        // targetRpm = SmartDashboard.getNumber("targetRpm", 0.0);

        shooter.setTargetRpm(targetRpm);
    }

}
