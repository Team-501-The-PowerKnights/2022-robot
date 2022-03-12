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
    private double duration;
    // Speed to drive
    private double speed = 0.30;  // default
    private final double turn = 0.0;  // no turn component

    // Timer to count it down during execute()
    private TimerFromPeriod timer;

    protected DriveForwardTimed() {
        // Prevent direct instantiation 
    }

    /**
     * Creates an instance of a class to dive forward at the default
     * speed.
     * 
     * @param duration - duration to drive (seconds)
     */
    public DriveForwardTimed(double duration) {
        logger.info("constructing {} for {} {}", getName(), duration, speed);

        setValues(duration, speed);

        logger.info("constructed");
    }

    /**
     * Creates an instance of the class to drive forward at the specified
     * speed.
     * 
     * @param duration - duration to drive (seconds)
     * @param speed - speed to drive
     */
    public DriveForwardTimed(double duration, double speed) {
        logger.info("constructing {} for {} {}", getName(), duration, speed);

        setValues(speed, duration);

        logger.info("constructed");
    }

    private void setValues(double duration, double speed) {
        this.duration = duration;
        if ( speed < 0.0 ) {
            speed = Math.abs(speed);
        }
        this.speed = speed;
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
