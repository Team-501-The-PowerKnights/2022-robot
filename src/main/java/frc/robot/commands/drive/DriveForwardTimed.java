/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.drive;


import frc.robot.commands.intake.IntakeDoNothing;
import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Add your docs here.
 */
public class DriveForwardTimed extends DriveCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(DriveForwardTimed.class.getName());

    // 
    private final double timeInSeconds;
    //
    private long executeCount;

    public DriveForwardTimed(double seconds) {
        logger.info("constructing {} for {}", getName(), seconds);

        timeInSeconds = seconds;

        logger.info("constructed");
    }

    // FIXME: make this a base class or something
    private long secondsToClicks (double seconds) {
        return (long)(seconds * 50.0);  // @ 50 Hz
    }

    @Override
    public void initialize() {
        super.initialize();

        executeCount = secondsToClicks(timeInSeconds);
    }

    @Override
    public void execute() {
        super.execute();

        double speed = 0.3;
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
