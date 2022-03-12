/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.drive;


import frc.robot.utils.TimerFromPeriod;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Add your docs here.
 */
public class DriveForwardTimed extends DriveCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(DriveForwardTimed.class.getName());

    // Duration to execute (in seconds)
    private final double duration;
    // Timer to count it down during execute()
    private TimerFromPeriod timer;

    public DriveForwardTimed(double seconds) {
        logger.info("constructing {} for {}", getName(), seconds);

        duration = seconds;

        logger.info("constructed");
    }

    @Override
    public void initialize() {
        super.initialize();

        timer = new TimerFromPeriod(duration);
    }

    @Override
    public void execute() {
        super.execute();

        timer.nextTic();
    }

    @Override
    protected void firstExecution() {
        logger.trace("drive.drive() called in firstExecution()");

        double speed = 0.3;
        double turn = 0.0;

        drive.drive(speed, turn);
    }

    @Override
    public boolean isFinished() {
        return timer.isExpired();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        drive.stop();
    }

}
