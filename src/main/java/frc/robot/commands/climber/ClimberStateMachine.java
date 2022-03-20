/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands.climber;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.ClimberPositionForLevel2Pose;
import frc.robot.commands.ClimberSetSubystemsPose;
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
        initState();  // same for now
    }

    public void enableClimberSequencing() {
        logger.info("enabling climber sequencing");
        climberEnabled = true;
        SmartDashboard.putBoolean(TelemetryNames.Misc.climberEnabled, climberEnabled);

        CommandScheduler.getInstance().schedule(true, new ClimberEnableSequencing());
    }

    public boolean isClimberEnabled() {
        return climberEnabled;
    }
    
    public void startClimberSequencing() {
        logger.info("starting climber sequencing");
        climberStarted = true;
        SmartDashboard.putBoolean(TelemetryNames.Misc.climberStarted, climberStarted);

        CommandScheduler.getInstance().schedule(true, new ClimberSetSubystemsPose());
        // Pneumatics aren't a subsystem, so commands don't work
        PCMFactory.getInstance().disabledInit();

        CommandScheduler.getInstance().schedule(true, new ClimberPositionForLevel2Pose(1.0));
    }

    public boolean isClimberStarted() {
        return climberStarted;
    }

}
