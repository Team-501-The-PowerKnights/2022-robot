/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands;


import frc.robot.commands.elevator.ElevatorDoNothing;
import frc.robot.commands.incrementor.IncrementorDoNothing;
import frc.robot.commands.intake.IntakeDoNothing;
import frc.robot.commands.intake.IntakeRetract;
import frc.robot.commands.shooter.ShooterDoNothing;
import frc.robot.commands.turret.TurretDoNothing;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Pose to get all subsystems to state ready for climbing.
 */
public class ClimberSetSubystemsPose extends PKParallelCommandGroup {
    
    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ClimberSetSubystemsPose.class.getName());

    public ClimberSetSubystemsPose() {
        logger.info("constructing");

        addCommands(new PKSequentialCommandGroup(new IntakeRetract(), new IntakeDoNothing()),
                    new IncrementorDoNothing(),
                    new ElevatorDoNothing(),
                    //new PKSequentialCommandGroup(new TurretHome(), new TurretDoNothing()),
                    new TurretDoNothing(),
                    new ShooterDoNothing()
                    );
 
        logger.info("constructed");
    }

}
