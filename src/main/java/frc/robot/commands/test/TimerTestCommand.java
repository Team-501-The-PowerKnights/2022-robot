/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.test;


import frc.robot.commands.PKCommandBase;
import frc.robot.utils.TimerFromPeriod;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Class to test the <code>TimerFromPeriod</code> class.
 * 
 * @see frc.robot.utils.TimerFromPeriod
 */
public class TimerTestCommand extends PKCommandBase {
        
    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(TimerTestCommand.class.getName());

    private TimerFromPeriod timer;

    private long startTime;
    private long stopTime;

    private final double duration = 1.0; // seconds

    public TimerTestCommand() {
        logger.info("constructing {}", getName());
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
        startTime = System.currentTimeMillis();
        logger.trace("*** timestamp for firstExecution() = {}", startTime);
    }

    @Override
    public boolean isFinished() {
        return timer.isExpired();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        stopTime = System.currentTimeMillis();
        logger.trace("*** timestamp for end() = {}", stopTime);
        logger.trace("*** result: request = {}, actual = {}",
                     duration, (stopTime - startTime)/1000.0);
    }

}
