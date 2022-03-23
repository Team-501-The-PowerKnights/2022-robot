/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.climber;


import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandGroupBase;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import frc.robot.OI;
import frc.robot.commands.ClimbFloorToLevel2Pose;
import frc.robot.commands.ClimbLevel2ToLevel3Pose;
import frc.robot.commands.ClimbLevel3ToLevel4Pose;
import frc.robot.commands.ClimbPositionForLevel2Pose;
import frc.robot.commands.ClimbSetSubystemsPose;
import frc.robot.subsystems.climber.ClimberFactory;
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
    // Flag for whether climber sequencing has started
    private boolean climberStarted;
    // Flag for whether climber sequencing is running
    private boolean climberRunning;

    private ClimberStateMachine() {
        logger.info("constructing");

        climbSteps = new ArrayList<>();

        /*
         * Can't init the state in the constructor, as there is a
         * race in the construction of the poses that make up the
         * sequence that depends on the state machine being already
         * constructed.
         */

        SmartDashboard.putBoolean(TelemetryNames.Climber.enabled, false);
        SmartDashboard.putBoolean(TelemetryNames.Climber.started, false);
        SmartDashboard.putBoolean(TelemetryNames.Climber.running, false);

        logger.info("constructed");
    }

    public void initState() {
        logger.trace("initialize climber control state");

        climberEnabled = false;
        SmartDashboard.putBoolean(TelemetryNames.Climber.enabled, climberEnabled);
        climberStarted = false;
        SmartDashboard.putBoolean(TelemetryNames.Climber.started, climberStarted);
        climberRunning = false;
        SmartDashboard.putBoolean(TelemetryNames.Climber.running, climberRunning);

        // Instantiate "debug stubs" for now with 5 second delays
        climbSteps.clear();
        climbSteps.add(new ClimbSetSubystemsPose());
        climbSteps.add(new ClimbPositionForLevel2Pose(1.0));
        climbSteps.add(new ClimbFloorToLevel2Pose(5.0));
        climbSteps.add(new ClimbLevel2ToLevel3Pose(5.0));
        climbSteps.add(new ClimbLevel3ToLevel4Pose(5.0));
        // Point to first step in the list
        stepIndex = 0;
        SmartDashboard.putString(TelemetryNames.Climber.sequenceName, "");
    }

    public void resetState() {
        initState(); // same for now
    }

    public void enableClimberSequencing() {
        logger.info("enabling climber sequencing");

        climberEnabled = true;
        SmartDashboard.putBoolean(TelemetryNames.Climber.enabled, climberEnabled);

        CommandScheduler.getInstance().schedule(true, new ClimberEnableSequencing());
    }

    public void beginClimberSequencing() {
        logger.info("beginning climber sequencing");

        climberStarted = true;
        SmartDashboard.putBoolean(TelemetryNames.Climber.started, climberStarted);

        //  PCMFactory.getInstance().disabledInit();

        CommandScheduler.getInstance().setDefaultCommand(ClimberFactory.getInstance(), new ClimberManualControl());
        OI.getInstance().configureClimbingButtonBindings();
    }

    public void endClimberSequencing() {
        logger.info("ending climber sequencing");
        climberRunning = false;
        SmartDashboard.putBoolean(TelemetryNames.Climber.running, climberRunning);
    }

    public boolean isClimberEnabled() {
        return climberEnabled;
    }

    //
    private final List<CommandGroupBase> climbSteps;
    //
    private int stepIndex;

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
        if (stepIndex < climbSteps.size()) {
            climberRunning = true;
            SmartDashboard.putBoolean(TelemetryNames.Climber.running, climberRunning);
    
            CommandGroupBase step = climbSteps.get(stepIndex);
            logger.info("starting next step via command: {}", step.getName());
            SmartDashboard.putString(TelemetryNames.Climber.sequenceName, step.getName());
            CommandScheduler.getInstance().schedule(true, step);

            return true;
        }
        else {
            logger.debug("no more steps to do; ignore");
            return false;
        }
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
        if (stepIndex < climbSteps.size()) {
            CommandGroupBase step = climbSteps.get(stepIndex);
            logger.info("ending current step: {} interrupted={}", step.getName(), interrupted);
            
            if ( !interrupted) {
                stepIndex++;
            }
        }
        else {
            logger.debug("all steps already ended; ignore");
        }
        climberRunning = false;
        SmartDashboard.putBoolean(TelemetryNames.Climber.running, climberRunning);
        SmartDashboard.putString(TelemetryNames.Climber.sequenceName, "");
    }

}
