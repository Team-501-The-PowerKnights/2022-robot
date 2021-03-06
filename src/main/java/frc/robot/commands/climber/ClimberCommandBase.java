/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.climber;


import frc.robot.commands.PKCommandBase;
import frc.robot.subsystems.climber.IClimberSubsystem;
import frc.robot.subsystems.climber.ClimberFactory;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Add your docs here.
 */
abstract class ClimberCommandBase extends PKCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ClimberCommandBase.class.getName());

    // Handle to our subsystem
    protected final IClimberSubsystem climber;

    public ClimberCommandBase() {
        logger.info("constructing {}", getName());

        climber = ClimberFactory.getInstance();
        addRequirements(climber);

        logger.info("constructed");
    }

}
