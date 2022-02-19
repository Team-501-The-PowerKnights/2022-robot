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
public class DriveForwardTimed extends DriveCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(DriveForwardTimed.class.getName());

    //
    private long executeCount;

    public DriveForwardTimed() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    @Override
    public void initialize() {
        super.initialize();

        // 4 seconds = 200 * 20 msec (@ 50 Hz)
        executeCount = 180;
    }

    @Override
    public void execute() {
        super.execute();

        double speed = 0.3; //0.4
        double turn = 0.0;

        drive.drive(speed, turn);

        --executeCount;
    }

    @Override
    public boolean isFinished() {
        return (executeCount > 0 ? false : true);
    }

    @Override
    public void end(boolean interrupted) {
        // Stop the drive
        drive.stop();

        super.end(interrupted);
    }

}
