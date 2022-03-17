/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.incrementor;


import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Add your docs here.
 */
public class IncrementorRunToFull extends IncrementorCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(IncrementorRunToFull.class.getName());

    public IncrementorRunToFull() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    private static double counts = 0;

    @Override
    public void initialize() {
        counts = 0;
    }

    @Override
    public void execute() {
        super.execute();

        counts++;
    }

    @Override
    protected void firstExecution() {
        logger.trace("incrementer.increment() called in firstExecution()");
        incrementer.increment();
    }

    @Override
    public boolean isFinished() {
        return incrementer.isFull() || counts >= 50;
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        incrementer.stop();
    }

}
