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


public class ElevatorLift extends ElevatorCommandBase {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ElevatorLift.class.getName());

    public ElevatorLift() {
        logger.info("constructing {}", getName());

        logger.info("constructed");
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    protected void firstExecution() {
        logger.trace("elevator.lift() called in firstExecution()");
        elevator.lift();
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        elevator.stop();
    }

}
