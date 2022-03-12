/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands;


import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Add your docs here.
 */
public class InvalidButton extends PKCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(InvalidButton.class.getName());

    // Something for unique identification
    @SuppressWarnings("unused")
    private final String button;

    public InvalidButton(String button) {
        logger.info("constructing {}", getName());

        this.button = button;

        logger.warn("for button {}", button);
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
