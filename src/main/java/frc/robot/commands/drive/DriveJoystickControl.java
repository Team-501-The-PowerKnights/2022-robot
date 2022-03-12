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


public class DriveJoystickControl extends DriveOICommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(DriveJoystickControl.class.getName());

    /**
     * Creates a new DriveJoystickControl.
     */
    public DriveJoystickControl() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    @Override
    public void execute() {
        super.execute();

        double speed = oi.getDriveSpeed();
        double turn = oi.getDriveTurn();

        drive.drive(speed, turn);
    }

}
