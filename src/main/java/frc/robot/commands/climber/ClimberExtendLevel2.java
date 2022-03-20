/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.climber;


import frc.robot.utils.TimerFromPeriod;
import riolog.PKLogger;
import riolog.RioLogger;


public class ClimberExtendLevel2 extends ClimberCommandBase {
       
    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ClimberRetract.class.getName());

    // Timer to count it down during execute()
    private TimerFromPeriod timer;
    
    public ClimberExtendLevel2() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    @Override
    public void initialize() {
        super.initialize();

        timer = new TimerFromPeriod(2.0);  // make different than drive
    }
    
    @Override
    public void execute() {
        super.execute();

        timer.nextTic();
    }

    @Override
    protected void firstExecution() {
        logger.trace("climber.extend() called in firstExecution()");
        //climber.???????();
    }

    @Override
    public boolean isFinished() {
        return timer.isExpired();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        climber.stop();
    }

}
