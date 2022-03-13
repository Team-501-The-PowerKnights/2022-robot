/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.shooter;


import frc.robot.commands.PKCommandBase;
import frc.robot.subsystems.shooter.IShooterSubsystem;
import frc.robot.subsystems.shooter.ShooterFactory;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Add your docs here.
 */
abstract class ShooterCommandBase extends PKCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ShooterCommandBase.class.getName());

    // Handle to our subsystem
    protected final IShooterSubsystem shooter;

    public ShooterCommandBase() {
        logger.info("constructing {}", getName());

        shooter = ShooterFactory.getInstance();
        addRequirements(shooter);

        logger.info("constructed");
    }

}
