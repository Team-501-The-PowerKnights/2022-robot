/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.climber;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.ClimbFloorToLevel2Pose;
import frc.robot.commands.ClimbLevel2ToLevel3Pose;
import frc.robot.commands.ClimbLevel3ToLevel4Pose;
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
    // Flag for whether climber sequencing is paused
    private boolean climberPaused;
    // Flag for whether level 2 has been completed
    private boolean level2Climbed;
    // Flag for whether level 3 has been completed
    private boolean level3Climbed;

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
        climberPaused = false;
        SmartDashboard.putBoolean(TelemetryNames.Misc.climberPaused, climberPaused);
        level2Climbed = false;
        SmartDashboard.putBoolean(TelemetryNames.Misc.level2Climbed, level2Climbed);
        level3Climbed = false;
        SmartDashboard.putBoolean(TelemetryNames.Misc.level3Climbed, level3Climbed);
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

    public void pause() {
        climberPaused = true;
        CommandScheduler.getInstance().schedule(true, new ClimberDoNothing());
    }

    public void resume() {
        climberPaused = false;
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

    public void climbNextLevel() {
        if (level3Climbed) {
            CommandScheduler.getInstance().schedule(true, new ClimbLevel3ToLevel4Pose());
        } else if (level2Climbed) {
            CommandScheduler.getInstance().schedule(true, new ClimbLevel2ToLevel3Pose());
        } else {
            CommandScheduler.getInstance().schedule(true, new ClimbFloorToLevel2Pose());
        }
    }

    public boolean isClimberEnabled() {
        return climberEnabled;
    }

    public boolean isClimberStarted() {
        return climberStarted;
    }

}
