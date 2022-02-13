/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.drive;


import frc.robot.commands.IIandTCommand;
import frc.robot.commands.PKManualCommand;
import frc.robot.subsystems.drive.DriveFactory;
import frc.robot.subsystems.drive.IDriveSubsystem;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Add your docs here.
 */
public class IandTDriveJoystickControl extends PKManualCommand implements IIandTCommand {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(DriveJoystickControl.class.getName());

    // Handle to our subsystem
    private IDriveSubsystem drive;

    /**
     * Creates a new DriveJoystickControl.
     */
    public IandTDriveJoystickControl() {
        logger.info("constructing {}", getName());

        drive = DriveFactory.getInstance();
        addRequirements(drive);

        logger.info("constructed");
    }

    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
        super.execute();

        double speed = oi.getDriveSpeed();
        double turn = oi.getDriveTurn();

        drive.drive(11, speed);
    }

    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
        return false;
    }

}
