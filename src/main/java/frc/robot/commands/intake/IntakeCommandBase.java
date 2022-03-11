/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.intake;


import frc.robot.commands.PKCommandBase;
import frc.robot.subsystems.intake.IIntakeSubsystem;
import frc.robot.subsystems.intake.IntakeFactory;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Add your docs here.
 */
abstract class IntakeCommandBase extends PKCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(IntakeCommandBase.class.getName());

    // Handle to our subsystem
    protected final IIntakeSubsystem intake;

    public IntakeCommandBase() {
        logger.info("constructing {}", getName());

        intake = IntakeFactory.getInstance();
        addRequirements(intake);

        logger.info("constructed");
    }

}
