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
 * A <code>Command</code> for driving under joystick control, but limited
 * to only forward and backward (via <code>speed</code> with the 
 * <code>turn</code> held at <code>0</code>.
 */
public class DriveStraightJoystickControl extends DriveOICommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(DriveStraightJoystickControl.class.getName());

    /**
     * Creates a new DriveStraightJoystickControl.
     */
    public DriveStraightJoystickControl() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    @Override
    public void execute() {
        super.execute();

        double speed = oi.getDriveSpeed();

        drive.drive(speed, 0.0);
    }

}
