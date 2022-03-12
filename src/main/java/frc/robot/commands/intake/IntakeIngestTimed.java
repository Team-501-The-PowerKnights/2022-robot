/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.intake;


import frc.robot.utils.TimerFromPeriod;

import riolog.PKLogger;
import riolog.RioLogger;


public class IntakeIngestTimed extends IntakeCommandBase {
    
    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(IntakeIngestTimed.class.getName());

    // Duration to execute (in seconds)
    private final double duration;
    // Timer to count it down during execute()
    private TimerFromPeriod timer;


    public IntakeIngestTimed(double seconds) {
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
        logger.trace("intake.extend() & intake.pullin called in firstExecution()");
        intake.extend();
        intake.pullIn();
    }

    @Override
    public boolean isFinished() {
        return timer.isExpired();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        intake.stop();
        intake.retract();
    }

}
