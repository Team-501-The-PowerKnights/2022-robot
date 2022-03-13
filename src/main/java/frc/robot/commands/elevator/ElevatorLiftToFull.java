/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.elevator;


import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Add your docs here.
 */
public class ElevatorLiftToFull extends ElevatorCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ElevatorLiftToFull.class.getName());

    public ElevatorLiftToFull() {
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
        logger.trace("elevator.lift() called in firstExecution()");
        elevator.lift();
    }

    @Override
    public boolean isFinished() {
        return elevator.isFull() || counts >= 250;
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        elevator.stop();
    }

}
