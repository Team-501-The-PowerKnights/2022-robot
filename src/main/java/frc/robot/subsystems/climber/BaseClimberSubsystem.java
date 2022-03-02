/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.climber;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.commands.climber.ClimberDoNothing;
import frc.robot.properties.PKProperties;
import frc.robot.properties.PropertiesManager;
import frc.robot.subsystems.SubsystemNames;
import frc.robot.telemetry.TelemetryNames;
import frc.robot.utils.PKStatus;

import riolog.PKLogger;
import riolog.RioLogger;

/**
 * Add your docs here.
 */
abstract class BaseClimberSubsystem extends SubsystemBase implements IClimberSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(BaseClimberSubsystem.class.getName());

    /** Our subsystem's name **/
    protected static final String myName = SubsystemNames.climberName;

    BaseClimberSubsystem() {
        logger.info("constructing");

        logger.info("constructed");
    }

    /** Objects to hold loaded default commands **/
    private static Command defaultAutoCommand;
    private static Command defaultTeleCommand;

    @Override
    public void loadDefaultCommand() {
        PKProperties props = PropertiesManager.getInstance().getProperties(myName);
        String myAutoClassName = props.getString("autoCommandName");
        if (myAutoClassName.isEmpty()) {
            logger.info("no class specified; go with subsystem default (do nothing)");
            myAutoClassName = new StringBuilder().append(myName).append("DoNothing").toString();
        }
        String myPkgName = ClimberDoNothing.class.getPackage().getName();
        String classToLoad = new StringBuilder().append(myPkgName).append(".").append(myAutoClassName).toString();
        logger.debug("class to load: {}", classToLoad);

        logger.info("constructing {} for {} subsystem", myAutoClassName, myName);
        Command ourAutoCommand;
        try {
            @SuppressWarnings("rawtypes")
            Class myClass = Class.forName(classToLoad);
            @SuppressWarnings("deprecation")
            Object myObject = myClass.newInstance();
            ourAutoCommand = (Command) myObject;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            logger.error("failed to load class; instantiating default stub for: {}", myName);
            ourAutoCommand = (Command) new ClimberDoNothing();
            SmartDashboard.putNumber(TelemetryNames.Climber.status, PKStatus.degraded.tlmValue);
        }

        defaultAutoCommand = ourAutoCommand;
        SmartDashboard.putString(TelemetryNames.Climber.autoCommand, ourAutoCommand.getClass().getSimpleName());

        String myTeleClassName = props.getString("teleCommandName");
        if (myTeleClassName.isEmpty()) {
            logger.info("no class specified; go with subsystem default (do nothing)");
            myTeleClassName = new StringBuilder().append(myName).append("DoNothing").toString();
        }
        myPkgName = ClimberDoNothing.class.getPackage().getName();
        classToLoad = new StringBuilder().append(myPkgName).append(".").append(myTeleClassName).toString();
        logger.debug("class to load: {}", classToLoad);

        logger.info("constructing {} for {} subsystem", myTeleClassName, myName);
        Command ourTeleCommand;
        try {
            @SuppressWarnings("rawtypes")
            Class myClass = Class.forName(classToLoad);
            @SuppressWarnings("deprecation")
            Object myObject = myClass.newInstance();
            ourTeleCommand = (Command) myObject;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            logger.error("failed to load class; instantiating default stub for: {}", myName);
            ourTeleCommand = (Command) new ClimberDoNothing();
            SmartDashboard.putNumber(TelemetryNames.Climber.status, PKStatus.degraded.tlmValue);
        }

        defaultTeleCommand = ourTeleCommand;
        SmartDashboard.putString(TelemetryNames.Climber.teleCommand, ourTeleCommand.getClass().getSimpleName());
    }

    @Override
    public void loadDefaultAutoCommand() {
        setDefaultCommand(defaultAutoCommand);
    }

    @Override
    public void loadDefaultTeleCommand() {
        setDefaultCommand(defaultTeleCommand);
    }

    protected void loadPreferences() {
        @SuppressWarnings("unused")
        double v;

        logger.info("new preferences for {}:", myName);
    }

    private double tlmSpeed = 0.0;

    protected void setTlmSpeed(double speed) {
        tlmSpeed = speed;
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putNumber(TelemetryNames.Climber.speed, tlmSpeed);
    }

    @Override
    public void updatePreferences() {
        loadPreferences();
    }

}
