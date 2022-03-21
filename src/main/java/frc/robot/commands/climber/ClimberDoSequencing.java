/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.climber;


import edu.wpi.first.wpilibj2.command.CommandScheduler;

import frc.robot.commands.PKCommandBase;

import riolog.PKLogger;
import riolog.RioLogger;


public class ClimberDoSequencing extends PKCommandBase {
    
    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ClimberDoSequencing.class.getName());

    // Handle to the state machine we interact with.
    private final ClimberStateMachine csm;

    // Flag for whether we are done combining or not
    private boolean done;

    public ClimberDoSequencing() {
        logger.info("constructing {}", getName());

        csm = ClimberStateMachine.getInstance();

        logger.info("constructed");
    }

    @Override
    public void initialize() {
        super.initialize();

        done = false;
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    protected void firstExecution() {
        logger.trace("climber.climb() called in firstExecution()");

        // May already be done?
        done = csm.doNextStep();
        // TODO: Find a way to unschedule this command?
    }

    @Override
    public boolean isFinished() {
        return done;
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        // This is true because the gamepad button was released
        csm.endCurrentStep(true);

        // This command will interrupt any schedule "real" command
        CommandScheduler.getInstance().schedule(new ClimberDoNothing());
    }

}
