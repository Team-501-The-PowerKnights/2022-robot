/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands;

import org.slf4j.Logger;

import frc.robot.subsystems.elevator.ElevatorFactory;
import frc.robot.subsystems.elevator.IElevatorSubsystem;
import frc.robot.subsystems.incrementer.IIncrementerSubsystem;
import frc.robot.subsystems.incrementer.IncrementerFactory;
import frc.robot.subsystems.shooter.IShooterSubsystem;
import frc.robot.subsystems.shooter.ShooterFactory;

import riolog.RioLogger;

public class FirePoseNoVision extends PKCommandBase {

    /** Our classes' logger **/
    private static final Logger logger = RioLogger.getLogger(FirePoseNoVision.class.getName());

    private final IShooterSubsystem shooter;
    private final IIncrementerSubsystem incrementer;
    private final IElevatorSubsystem elevator;

    public FirePoseNoVision() {
        logger.info("constructing {}", getName());

        shooter = ShooterFactory.getInstance();
        incrementer = IncrementerFactory.getInstance();
        elevator = ElevatorFactory.getInstance();

        addRequirements(shooter, incrementer, elevator);

        logger.info("constructed {}", getName());
    }

    @Override
    public void execute() {
        super.execute();

        shooter.setSpeed(29, 0.50);

        incrementer.increment();
        elevator.liftToLimit();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        incrementer.stop();
        elevator.stop();
    }

}
