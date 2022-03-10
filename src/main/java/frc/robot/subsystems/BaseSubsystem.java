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

import frc.robot.commands.DoNothing;
import frc.robot.commands.PKCommandBase;
import frc.robot.properties.PKProperties;
import frc.robot.properties.PropertiesManager;
import frc.robot.telemetry.TelemetryNames;
import frc.robot.utils.PKStatus;

import riolog.PKLogger;
import riolog.RioLogger;


public abstract class BaseSubsystem implements ISubsystem {
    
    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(BaseSubsystem.class.getName());

    /** Our subsystem's name **/
    protected final String myName;

    /** Objects to hold loaded default commands **/
    protected static Command defaultAutoCommand;
    protected static Command defaultTeleCommand;

    public BaseSubsystem(String name) {
        logger.info("constructing");

        myName = name;

        logger.info("constructed");
    }

    @Override
    public void setDefaultAutoCommand() {
        setDefaultCommand(defaultAutoCommand);
    }

    @Override
    public void setDefaultTeleCommand() {
        setDefaultCommand(defaultTeleCommand);
    }
    
    /**
     * Called to load the default commands for the subsystem (both
     * autonomous and teleop); the values are determined from the
     * properties file and loaded dynamically.
     * 
     * @param doNothingClass - class to default to if not found or errors
     **/
    protected void loadDefaultCommands(Class<? extends PKCommandBase> doNothingClass) {
        logger.debug("loading for {}", myName);

        PKProperties props = PropertiesManager.getInstance().getProperties(myName);

        String myAutoClassName = props.getString("autoCommandName");
        if (myAutoClassName.isEmpty()) {
            logger.info("no class specified; go with subsystem default (do nothing)");
            myAutoClassName = new StringBuilder().append(myName).append("DoNothing").toString();
        }
        defaultAutoCommand = loadCommandClass(myAutoClassName, doNothingClass);
        SmartDashboard.putString(TelemetryNames.Climber.autoCommand, defaultAutoCommand.getClass().getSimpleName());

        String myTeleClassName = props.getString("teleCommandName");
        if (myTeleClassName.isEmpty()) {
            logger.info("no class specified; go with subsystem default (do nothing)");
            myTeleClassName = new StringBuilder().append(myName).append("DoNothing").toString();
        }
        defaultTeleCommand = loadCommandClass(myTeleClassName, doNothingClass);
        SmartDashboard.putString(TelemetryNames.Climber.teleCommand, defaultTeleCommand.getClass().getSimpleName());
    }

    /**
     * Dynamically load and instantiate an object of the specified class,
     * returning an instance of the <code>doNothingClass</code> in case of
     * any errors. If all else fails, then return an instance of the base
     * <code>DoNothingClass</code>.
     * 
     * @param nameOfClass
     * @param doNothingClass
     * @return
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
