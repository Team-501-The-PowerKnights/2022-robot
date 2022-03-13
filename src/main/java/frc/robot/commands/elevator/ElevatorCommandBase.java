/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.elevator;


import frc.robot.commands.PKCommandBase;
import frc.robot.subsystems.elevator.IElevatorSubsystem;
import frc.robot.subsystems.elevator.ElevatorFactory;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Add your docs here.
 */
abstract class ElevatorCommandBase extends PKCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ElevatorCommandBase.class.getName());

    // Handle to our subsystem
    protected final IElevatorSubsystem elevator;

    public ElevatorCommandBase() {
        logger.info("constructing {}", getName());

        elevator = ElevatorFactory.getInstance();
        addRequirements(elevator);

        logger.info("constructed");
    }

}
