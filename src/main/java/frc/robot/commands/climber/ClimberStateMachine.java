/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import frc.robot.commands.ClimbPositionForLevel2Pose;
import frc.robot.commands.ClimbSetSubystemsPose;
import frc.robot.modules.pcm.PCMFactory;
import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;

/**
 * 
 */
public class ClimberStateMachine {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ClimberStateMachine.class.getName());

    /** Singleton instance of class for all to use **/
    private static ClimberStateMachine ourInstance;

    /**
     * Constructs instance of the state machine. Assumed to be called before
     * any usage of the state machine; and verifies only called once.
     **/
    public static synchronized void constructInstance() {
        if (ourInstance != null) {
            throw new IllegalStateException("ClimberStateMachine Already Constructed");
        }

        ourInstance = new ClimberStateMachine();
    }

    /**
     * Returns the singleton instance of the state machine. If it hasn't been
     * constructed yet, throws an <code>IllegalStateException</code>.
     *
     * @return singleton instance of subsystem
     **/
    public static ClimberStateMachine getInstance() {
        if (ourInstance == null) {
            throw new IllegalStateException("ClimberState Machine Not Constructed Yet");
        }

        return ourInstance;
    }

    // Flag for whether climber sequencing is enabled
    private boolean climberEnabled;
    // Flag for whether climber sequencing is started
    private boolean climberStarted;

    private ClimberStateMachine() {
        logger.info("constructing");

        initState();

        logger.info("constructed");
    }

    public void initState() {
        logger.trace("initialize climber control state");
        climberEnabled = false;
        SmartDashboard.putBoolean(TelemetryNames.Misc.climberEnabled, climberEnabled);
        climberStarted = false;
        SmartDashboard.putBoolean(TelemetryNames.Misc.climberStarted, climberStarted);
    }

    public void resetState() {
        initState(); // same for now
    }

    public void enableClimberSequencing() {
        logger.info("enabling climber sequencing");
        climberEnabled = true;
        SmartDashboard.putBoolean(TelemetryNames.Misc.climberEnabled, climberEnabled);

        CommandScheduler.getInstance().schedule(true, new ClimberEnableSequencing());
    }

    public void startClimberSequencing() {
        logger.info("starting climber sequencing");
        climberStarted = true;
        SmartDashboard.putBoolean(TelemetryNames.Misc.climberStarted, climberStarted);

        // Home, store, disable, etc. all the subsystems not active in climb
        CommandScheduler.getInstance().schedule(true, new ClimbSetSubystemsPose());
        // Pneumatics aren't a subsystem, so commands don't work
        PCMFactory.getInstance().disabledInit();

        // Moves the robot to position and extends the climber
        CommandScheduler.getInstance().schedule(true, new ClimbPositionForLevel2Pose(1.0));
    }

    public boolean isClimberEnabled() {
        return climberEnabled;
    }

    public boolean isClimberStarted() {
        return climberStarted;
    }

    /**
     * Executes the next queued command step in the sequence for 
     * climbing. If there are no more commands to execute, it returns
     * false;
     * <p>
     * At the completion of the command step that is scheduled, the
     * call to {#link endCurrentStep(boolean)} must be called to
     * close the feedback loop.
     * <p>
     * If the previous command was interrupted and failed to complete,
     * the same command will be restarted. So individual commands are 
     * responsible for being able to "re-start".
     * 
     * @return true if another command will be run; false if all done
     */
    public boolean doNextStep() {
        logger.info("starting next step via command: {}", "notImplementedYet");
        return true;
    }

    /**
     * Signals the end of the currently executing step, with the
     * argument specifying whether the command was interrupted or
     * not.
     * <p>
     * If the command was interrupted, the state machine is not
     * advanced, and the next call to {#link doNextStep()} will
     * schedule the same command.
     * 
     * @param interrupted
     */
    public void endCurrentStep(boolean interrupted) {
        logger.info("ending current step: interrupted={}", interrupted);
    }

}
