/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.drive;


import frc.robot.commands.PKCommandBase;
import frc.robot.subsystems.drive.DriveFactory;
import frc.robot.subsystems.drive.IDriveSubsystem;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Add your docs here.
 */
abstract class DriveCommandBase extends PKCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(DriveCommandBase.class.getName());

    // Handle to our subsystem
    protected IDriveSubsystem drive;

    public DriveCommandBase() {
        logger.info("constructing {}", getName());

        drive = DriveFactory.getInstance();

        addRequirements(drive);

        logger.info("constructed");
    }

}
