/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems;


import java.lang.reflect.InvocationTargetException;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

import frc.robot.commands.DoNothing;
import frc.robot.commands.PKCommandBase;
import frc.robot.properties.PKProperties;
import frc.robot.properties.PropertiesManager;
import frc.robot.telemetry.TelemetryNames;
import frc.robot.utils.PKStatus;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * A base class for subsystems that handles registration in the constructor, deals
 * with the default commands, and provides default methods for the
 * {@link frc.robot.IModeFollower IRobotModes} interface for notifications of
 * mode transitions.
 */
public abstract class BaseSubsystem implements ISubsystem {
    
    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(BaseSubsystem.class.getName());

    /** Our subsystem's name **/
    protected final String myName;

    /** Objects to hold loaded default commands **/
    protected Command defaultAutoCommand;
    protected Command defaultTeleCommand;

    public BaseSubsystem(String name) {
        logger.info("constructing");

        myName = name;

        // Register subsystem with 
        CommandScheduler.getInstance().registerSubsystem(this);

        logger.info("constructed");
    }

    @Override
    public void disabledInit() {
        logger.info("initializing disabled for {}", myName);

        validateCalibration();

        logger.info("initialized disabled for {}", myName);
    }

    @Override
    public void disabledExit() {
        logger.info("exiting disabled for {}", myName);
        
        logger.info("exited disabled for {}", myName);
    }

    @Override
    public void autonomousInit() {
        logger.info("initializing auto for {}", myName);

        logger.info("set default auto command to {}", defaultAutoCommand.getName());
        setDefaultCommand(defaultAutoCommand);

        updatePreferences();

        logger.info("initialized auto for {}", myName);
    }

    @Override
    public void autonomousExit() {
        logger.info("exiting auto for {}", myName);
        
        logger.info("exited auto for {}", myName);
    }

    @Override
    public void teleopInit() {
        logger.info("initializing teleop for {}", myName);

        logger.info("set default teleop command to {}", defaultTeleCommand.getName());
        setDefaultCommand(defaultTeleCommand);

        updatePreferences();
        
        logger.info("initialized teleop for {}", myName);
    }

    @Override
    public void teleopExit() {
        logger.info("exiting teleop for {}", myName);
        
        logger.info("exited teleop for {}", myName);
    }

    @Override
    public void testInit() {
        logger.info("initializing test for {}", myName);

        updatePreferences();
        
        logger.info("initialized test for {}", myName);
    }

    @Override
    public void testExit() {
        logger.info("exiting test for {}", myName);
        
        logger.info("exited test for {}", myName);
    }
    
    /**
     * Called to load the default commands for the subsystem (both
     * autonomous and teleop); the values are determined from the
     * properties file and loaded dynamically.
     * 
     * @param doNothingClass - class to default to if not found or errors
     **/
    protected void loadDefaultCommands(Class<? extends PKCommandBase> doNothingClass) {
        PKProperties props = PropertiesManager.getInstance().getProperties(myName);

        String myAutoClassName = props.getString("autoCommandName");
        logger.debug("{} attempting load of default auto {}", myName, myAutoClassName);
        if (myAutoClassName.isEmpty()) {
            logger.info("no class specified; go with subsystem default (do nothing)");
            myAutoClassName = new StringBuilder().append(myName).append("DoNothing").toString();
        }
        defaultAutoCommand = loadCommandClass(myAutoClassName, doNothingClass);

        String myTeleClassName = props.getString("teleCommandName");
        logger.debug("{} attempting load of default teleop {}", myName, myTeleClassName);
        if (myTeleClassName.isEmpty()) {
            logger.info("no class specified; go with subsystem default (do nothing)");
            myTeleClassName = new StringBuilder().append(myName).append("DoNothing").toString();
        }
        defaultTeleCommand = loadCommandClass(myTeleClassName, doNothingClass);
    }

    /**
     * Dynamically load and instantiate an object of the specified class,
     * returning an instance of the <code>doNothingClass</code> in case of
     * any errors. If all else fails, then return an instance of the base
     * <code>DoNothingClass</code>.
     * 
     * @param nameOfClass - name of class to load
     * @param doNothingClass - default command class to use ('do nothing')
     * @return class successfully loaded
     */
    private PKCommandBase loadCommandClass(String nameOfClass, Class<? extends PKCommandBase> doNothingClass) {
        String myPkgName = doNothingClass.getPackage().getName();
        String classToLoad = new StringBuilder().append(myPkgName).append(".").append(nameOfClass).toString();
        logger.debug("class to load: {}", classToLoad);

        logger.info("constructing {} for {} subsystem", nameOfClass, myName);
        PKCommandBase loadedCommand;
        try {
            @SuppressWarnings("rawtypes")
            Class myClass = Class.forName(classToLoad);
            @SuppressWarnings("deprecation")
            Object myObject = myClass.newInstance();
            loadedCommand = (PKCommandBase) myObject;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            logger.error("failed to load class; instantiating default stub for: {}", myName);
            try {
                loadedCommand = (PKCommandBase) doNothingClass.getDeclaredConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException | NoSuchMethodException | SecurityException e) {
                logger.error("failed to load do nothing class; instantiating stub for: {}", myName);
                loadedCommand = new DoNothing();
            }
            SmartDashboard.putNumber(TelemetryNames.Climber.status, PKStatus.degraded.tlmValue);
        }
        return loadedCommand;
    }
    
}
