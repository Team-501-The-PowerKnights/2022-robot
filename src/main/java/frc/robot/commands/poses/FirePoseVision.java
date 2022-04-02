/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.poses;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Robot;
import frc.robot.commands.PKCommandBase;
import frc.robot.sensors.vision.IVisionSensor;
import frc.robot.sensors.vision.VisionFactory;
import frc.robot.subsystems.elevator.ElevatorFactory;
import frc.robot.subsystems.elevator.IElevatorSubsystem;
import frc.robot.subsystems.incrementor.IIncrementorSubsystem;
import frc.robot.subsystems.incrementor.IncrementorFactory;
import frc.robot.subsystems.shooter.IShooterSubsystem;
import frc.robot.subsystems.shooter.ShooterFactory;
import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;


public class FirePoseVision extends PKCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(FirePoseVision.class.getName());

    private final IVisionSensor vision;

    private final IShooterSubsystem shooter;
    private final IIncrementorSubsystem incrementer;
    private final IElevatorSubsystem elevator;

    public FirePoseVision() {
        logger.info("constructing {}", getName());

        vision = VisionFactory.getInstance();

        shooter = ShooterFactory.getInstance();
        incrementer = IncrementorFactory.getInstance();
        elevator = ElevatorFactory.getInstance();

        addRequirements(shooter, incrementer, elevator);

        logger.info("constructed {}", getName());
    }

    @Override
    public void execute() {
        super.execute();

        double y = vision.getY();

        // double speed = 0.483 + (-6.55E-03 * y) + (1.27E-03 * (Math.pow(y, 2))) +
        //         ((-8.18E-05) * (Math.pow(y, 3)));
        // 0.483 + -6.55E-03x + 1.27E-03x^2 + -8.18E-05x^3

        double speed = 0.48;
        if (y >= 7.1) {
            speed = 0.48;
        } else if (y >= 3.1) {
            speed = 0.49;
        } else {
            speed = 0.5;
        }
        Robot.shooterSetSpeed = speed;

        // double speed = SmartDashboard.getNumber("Shooter.tuning", 0.5); // Tuning only
        // speed += 0.015;
        // if (speed > 0.46 && speed < 0.64) {
            // // Extra boost without mucking on formula
            // speed += 0.02;
            // Robot.shooterSetSpeed = speed;
        // }

        shooter.setSpeed(29, Robot.shooterSetSpeed);

        boolean visionGood = (vision.isActive() && vision.isLocked()) || !(vision.isActive());
        if (visionGood) {
            incrementer.increment();
            // elevator.liftToLimit();
            elevator.lift();
        } else {
            incrementer.stop();
            elevator.stop();
        }
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        // shooter goes to default (idle) when command ends
        incrementer.stop();
        elevator.stop();
    }

}
