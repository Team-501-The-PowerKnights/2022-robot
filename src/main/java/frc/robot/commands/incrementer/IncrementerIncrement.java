/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.incrementer;

import frc.robot.subsystems.incrementer.IIncrementerSubsystem;

import riolog.PKLogger;
import riolog.RioLogger;

/**
 * Add your docs here.
 */
public class IncrementerIncrement extends IncrementerCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(IncrementerIncrement.class.getName());

    // Handle to our subsystem
    protected IIncrementerSubsystem incrementer;

    public IncrementerIncrement() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    @Override
    public void execute() {
        incrementer.increment();
    }

    @Override
    public void end(boolean interrupted) {
        incrementer.stop();
    }

}
