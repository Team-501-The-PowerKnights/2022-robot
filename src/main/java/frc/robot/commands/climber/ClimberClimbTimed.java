/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.climber;


import riolog.PKLogger;
import riolog.RioLogger;


public class ClimberClimbTimed extends ClimberCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ClimberClimbTimed.class.getName());

    // 
    private final double timeInSeconds;
    //
    private long executeCount;

    public ClimberClimbTimed(double seconds) {
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

        --executeCount;
    }

    @Override
    protected void firstExecution() {
        logger.trace("climber.climb() called in firstExecution()");
        climber.climb();
    }

    @Override
    public boolean isFinished() {
        return (executeCount > 0 ? false : true);
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        climber.stop();
    }

}
