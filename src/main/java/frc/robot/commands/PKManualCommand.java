/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands;


import frc.robot.OI;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * 
 */
public abstract class PKManualCommand extends PKCommandBase {

    /* Our classes logger */
    private static final PKLogger logger = RioLogger.getLogger(PKManualCommand.class.getName());

    // Handle to the OI object for access to joysticks
    protected final OI oi;

    protected PKManualCommand() {
        logger.info("constructing for {}", getName());

        oi = OI.getInstance();

        logger.info("constructed");
    }

}
