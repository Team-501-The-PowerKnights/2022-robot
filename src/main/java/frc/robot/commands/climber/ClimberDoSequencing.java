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

    // Flag for whether we are done climbing or not
    private boolean stepsToRun;

    public ClimberDoSequencing() {
        logger.info("constructing {}", getName());

        csm = ClimberStateMachine.getInstance();

        logger.info("constructed");
    }

    @Override
    public void initialize() {
        super.initialize();

        stepsToRun = false;
    }

    @Override
    public void execute() {
        super.execute();
    }

    @Override
    protected void firstExecution() {
        logger.trace("csm.doNextStep() called in firstExecution()");

        // May already be done?
        stepsToRun = csm.doNextStep();
        // TODO: Find a way to unschedule this command?
    }

    @Override
    public boolean isFinished() {
        // We never finish, unless the climb is complete
        return !stepsToRun;
    }

    @Override
    public void end(boolean interrupted) {
        super.end(interrupted);

        // This is true because the gamepad button was released
        csm.endCurrentStep(true);

        // This command will interrupt any schedule "real" command and
        // return the climber to manual control
        // CommandScheduler.getInstance().schedule(new ClimberDoNothing());
        CommandScheduler.getInstance().schedule(new ClimberManualControl());
        ClimberStateMachine.getInstance().endClimberSequencing();
    }

}
