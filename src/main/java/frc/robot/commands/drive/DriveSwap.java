/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.drive;


import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Add your docs here.
 */
public class DriveSwap extends DriveCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(DriveSwap.class.getName());

    // FIXME - Should pause a couple of cycles of execute to give time to coast out

    public DriveSwap() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    @Override
    public void execute() {
        super.execute();

        drive.swap();
    }

    @Override
    public boolean isFinished() {
        return true;
    }

}
