/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands;


import frc.robot.sensors.vision.IVisionSensor;
import frc.robot.sensors.vision.VisionFactory;
import frc.robot.subsystems.elevator.ElevatorFactory;
import frc.robot.subsystems.elevator.IElevatorSubsystem;
import frc.robot.subsystems.incrementer.IIncrementerSubsystem;
import frc.robot.subsystems.incrementer.IncrementerFactory;
import frc.robot.subsystems.shooter.IShooterSubsystem;
import frc.robot.subsystems.shooter.ShooterFactory;

import riolog.PKLogger;
import riolog.RioLogger;


public class FirePoseVision extends PKCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(FirePoseVision.class.getName());

    private final IVisionSensor vision;

    private final IShooterSubsystem shooter;
    private final IIncrementerSubsystem incrementer;
    private final IElevatorSubsystem elevator;

    public FirePoseVision() {
        logger.info("constructing {}", getName());

        vision = VisionFactory.getInstance();

        shooter = ShooterFactory.getInstance();
        incrementer = IncrementerFactory.getInstance();
        elevator = ElevatorFactory.getInstance();

        addRequirements(shooter, incrementer, elevator);

        logger.info("constructed {}", getName());
    }

    @Override
    public void execute() {
        super.execute();

        double y = vision.getY();
        double speed = 0.489 + (-0.0116 * y) + (0.0107 * (Math.pow(y, 2))) + ((-4.32E-03) * (Math.pow(y, 3)))
                + (2.07E-04 * Math.pow(y, 4)) + (2.34E-04 * Math.pow(y, 5)) + (-5.47E-05 * Math.pow(y, 6))
                + (4.68E-06 * Math.pow(y, 7)) + -1.41E-07 * (Math.pow(y, 8));
        shooter.setSpeed(29, speed);

        boolean visionGood = (vision.isActive() && vision.isLocked()) || !(vision.isActive());
        if (visionGood) {
            incrementer.increment();
            elevator.liftToLimit();
        } else {
            incrementer.stop();
            elevator.stop();
        }
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        incrementer.stop();
        elevator.stop();

        // Don't stop shooter (default is idle command)
        // shooter.stop();
    }

}
