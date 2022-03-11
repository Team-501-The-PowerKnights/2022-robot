/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.turret;


import frc.robot.commands.PKCommandBase;
import frc.robot.subsystems.turret.ITurretSubsystem;
import frc.robot.subsystems.turret.TurretFactory;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Add your docs here.
 */
abstract class TurretCommandBase extends PKCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(TurretCommandBase.class.getName());

    // Handle to our subsystem
    protected final ITurretSubsystem turret;

    public TurretCommandBase() {
        logger.info("constructing {}", getName());

        turret = TurretFactory.getInstance();
        addRequirements(turret);

        logger.info("constructed");
    }

}
