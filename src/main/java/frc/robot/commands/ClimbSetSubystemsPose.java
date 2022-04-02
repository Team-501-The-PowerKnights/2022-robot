/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands;


import frc.robot.commands.elevator.ElevatorDisable;
import frc.robot.commands.incrementor.IncrementorDisable;
import frc.robot.commands.intake.IntakeDisable;
import frc.robot.commands.intake.IntakeRetract;
import frc.robot.commands.shooter.ShooterDisable;
import frc.robot.commands.turret.TurretDisable;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Pose to get all subsystems to state ready for climbing.
 */
public class ClimbSetSubystemsPose extends ClimbBasePose {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ClimbSetSubystemsPose.class.getName());

    public ClimbSetSubystemsPose() {
        super();
        logger.info("constructing {}", getName());

        addCommands(
            // new PKParallelCommandGroup(
            //     new PKSequentialCommandGroup(new IntakeRetract(), new IntakeStop()),
            //     new IncrementorStop(),
            //     new ElevatorStop(),
            //     // new PKSequentialCommandGroup(new TurretHome(), new WaitCommand(0.5), new TurretStop()),
            //     new TurretStop(),
            //     new ShooterStop()
            //     )
            new PKParallelCommandGroup(
                new PKSequentialCommandGroup(new IntakeRetract(), new IntakeDisable()),
                new IncrementorDisable(),
                new ElevatorDisable(),
                // new PKSequentialCommandGroup(new TurretHome(), new WaitCommand(0.5), new TurretStop()),
                new TurretDisable(),
                new ShooterDisable()
                )
        );

        logger.info("constructed");
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        csm.endCurrentStep(interrupted);
        if (!interrupted) {
            csm.doNextStep();
        }
    }

}
