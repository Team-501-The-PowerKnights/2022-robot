/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.commands.drive.DriveDoNothing;
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
abstract class BaseDriveSubsystem extends SubsystemBase implements IDriveSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(BaseDriveSubsystem.class.getName());

    /** Our subsystem's name **/
    protected static final String myName = SubsystemNames.driveName;

    /** Default preferences for subystem **/
    private final double default_pid_P = 0.0;
    private final double default_pid_I = 0.0;
    private final double default_pid_D = 0.0;
    private final double default_pid_F = 0.0;

    /** PID for subystem **/
    protected double pid_P = 0;
    protected double pid_I = 0;
    protected double pid_D = 0;
    protected double pid_F = 0;

    BaseDriveSubsystem() {
        logger.info("constructing");

        logger.info("constructed");
    }

    /** Objects to hold loaded default commands **/
    private static Command defaultAutoCommand;
    private static Command defaultTeleCommand;

    @Override
    public void loadDefaultCommand() {
        PKProperties props = PropertiesManager.getInstance().getProperties(myName);
        String myClassName = props.getString("autoCommandName");
        String myPkgName = DriveDoNothing.class.getPackage().getName();
        String classToLoad = new StringBuilder().append(myPkgName).append(".").append(myClassName).toString();
        logger.debug("class to load: {}", classToLoad);

        logger.info("constructing {} for {} subsystem", myClassName, myName);
        Command ourAutoCommand;
        try {
            @SuppressWarnings("rawtypes")
            Class myClass = Class.forName(classToLoad);
            @SuppressWarnings("deprecation")
            Object myObject = myClass.newInstance();
            ourAutoCommand = (Command) myObject;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            logger.error("failed to load class; instantiating default stub for: {}", myName);
            ourAutoCommand = (Command) new DriveDoNothing();
            SmartDashboard.putNumber(TelemetryNames.Drive.status, PKStatus.degraded.tlmValue);
        }

        defaultAutoCommand = ourAutoCommand;
        SmartDashboard.putString(TelemetryNames.Drive.autoCommand, ourAutoCommand.getClass().getSimpleName());

        myClassName = props.getString("teleCommandName");
        myPkgName = DriveDoNothing.class.getPackage().getName();
        classToLoad = new StringBuilder().append(myPkgName).append(".").append(myClassName).toString();
        logger.debug("class to load: {}", classToLoad);

        logger.info("constructing {} for {} subsystem", myClassName, myName);
        Command ourTeleCommand;
        try {
            @SuppressWarnings("rawtypes")
            Class myClass = Class.forName(classToLoad);
            @SuppressWarnings("deprecation")
            Object myObject = myClass.newInstance();
            ourTeleCommand = (Command) myObject;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            logger.error("failed to load class; instantiating default stub for: {}", myName);
            ourTeleCommand = (Command) new DriveDoNothing();
            SmartDashboard.putNumber(TelemetryNames.Drive.status, PKStatus.degraded.tlmValue);
        }

        defaultTeleCommand = ourTeleCommand;
        SmartDashboard.putString(TelemetryNames.Drive.teleCommand, ourTeleCommand.getClass().getSimpleName());
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
        double v;

        logger.info("new preferences for {}:", myName);
        v = Preferences.getDouble(DrivePreferences.pid_P, default_pid_P);
        logger.info("{} = {}", DrivePreferences.pid_P, v);
        pid_P = v;
        v = Preferences.getDouble(DrivePreferences.pid_I, default_pid_I);
        logger.info("{} = {}", DrivePreferences.pid_I, v);
        pid_I = v;
        v = Preferences.getDouble(DrivePreferences.pid_D, default_pid_D);
        logger.info("{} = {}", DrivePreferences.pid_D, v);
        pid_D = v;
        v = Preferences.getDouble(DrivePreferences.pid_F, default_pid_F);
        logger.info("{} = {}", DrivePreferences.pid_F, v);
        pid_F = v;
    }

    protected double speed = 0.0;
    protected double turn = 0.0;
    protected double leftSpeed = 0.0;
    protected double rightSpeed = 0.0;

    @Override
    public void updateTelemetry() {
        SmartDashboard.putNumber(TelemetryNames.Drive.leftSpeed, leftSpeed);
        SmartDashboard.putNumber(TelemetryNames.Drive.rightSpeed, rightSpeed);
    }

    @Override
    public void updatePreferences() {
        loadPreferences();
    }

}
