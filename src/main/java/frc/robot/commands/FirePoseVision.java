/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands;

import org.slf4j.Logger;

import frc.robot.sensors.vision.IVisionSensor;
import frc.robot.sensors.vision.VisionFactory;
import frc.robot.subsystems.incrementer.IIncrementerSubsystem;
import frc.robot.subsystems.incrementer.IncrementerFactory;
import frc.robot.subsystems.shooter.IShooterSubsystem;
import frc.robot.subsystems.shooter.ShooterFactory;

import riolog.RioLogger;

public class FirePoseVision extends PKCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(FirePoseVision.class.getName());

    private final IVisionSensor vision;

    private final IShooterSubsystem shooter;
    private final IIncrementerSubsystem incrementer;

    public FirePoseVision() {
        logger.info("constructing {}", getName());

        vision = VisionFactory.getInstance();

        shooter = ShooterFactory.getInstance();
        incrementer = IncrementerFactory.getInstance();

        addRequirements(shooter, incrementer);

        logger.info("constructed {}", getName());
    }

    @Override
    public void execute() {
        super.execute();

        shooter.shoot();

        boolean visionGood = (vision.isActive() && vision.isLocked()) || !(vision.isActive());
        if (visionGood && shooter.atTargetVelocity()) {
            incrementer.increment();
        } else {
            // ballevator.liftToLimit();
            incrementer.stop();
        }
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        incrementer.stop();

        // Don't stop shooter (default is idle command)
        // shooter.stop();
    }

}
