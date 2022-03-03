/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.incrementer;


import frc.robot.commands.PKCommandBase;
import frc.robot.subsystems.incrementer.IIncrementerSubsystem;
import frc.robot.subsystems.incrementer.IncrementerFactory;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Add your docs here.
 */
abstract class IncrementerCommandBase extends PKCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(IncrementerCommandBase.class.getName());

    // Handle to our subsystem
    protected IIncrementerSubsystem incrementer;

    public IncrementerCommandBase() {
        logger.info("constructing {}", getName());

        incrementer = IncrementerFactory.getInstance();

        addRequirements(incrementer);

        logger.info("constructed");
    }

}
