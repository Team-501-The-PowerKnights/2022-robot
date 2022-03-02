/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.turret;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.commands.turret.TurretDoNothing;
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
abstract class BaseTurretSubsystem extends SubsystemBase implements ITurretSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(BaseTurretSubsystem.class.getName());

    /** Our subsystem's name **/
    protected static final String myName = SubsystemNames.turretName;

    /** Default preferences for subystem **/
    private final double default_pid_P = 0.5;
    private final double default_pid_I = 0.005;
    private final double default_pid_D = 1.0;
    private final double default_pid_F = 0.0;

    /** PID for subystem **/
    protected double pid_P = 0;
    protected double pid_I = 0;
    protected double pid_D = 0;
    protected double pid_F = 0;

    BaseTurretSubsystem() {
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
        String myPkgName = TurretDoNothing.class.getPackage().getName();
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
            ourAutoCommand = (Command) new TurretDoNothing();
            SmartDashboard.putNumber(TelemetryNames.Turret.status, PKStatus.degraded.tlmValue);
        }

        defaultAutoCommand = ourAutoCommand;
        SmartDashboard.putString(TelemetryNames.Turret.autoCommand, ourAutoCommand.getClass().getSimpleName());

        String myTeleClassName = props.getString("teleCommandName");
        if (myTeleClassName.isEmpty()) {
            logger.info("no class specified; go with subsystem default (do nothing)");
            myTeleClassName = new StringBuilder().append(myName).append("DoNothing").toString();
        }
        myPkgName = TurretDoNothing.class.getPackage().getName();
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
            ourTeleCommand = (Command) new TurretDoNothing();
            SmartDashboard.putNumber(TelemetryNames.Turret.status, PKStatus.degraded.tlmValue);
        }

        defaultTeleCommand = ourTeleCommand;
        SmartDashboard.putString(TelemetryNames.Turret.teleCommand, ourTeleCommand.getClass().getSimpleName());
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
        v = Preferences.getDouble(TurretPreferences.pid_P, default_pid_P);
        logger.info("{} = {}", TurretPreferences.pid_P, v);
        pid_P = v;
        v = Preferences.getDouble(TurretPreferences.pid_I, default_pid_I);
        logger.info("{} = {}", TurretPreferences.pid_I, v);
        pid_I = v;
        v = Preferences.getDouble(TurretPreferences.pid_D, default_pid_D);
        logger.info("{} = {}", TurretPreferences.pid_D, v);
        pid_D = v;
        v = Preferences.getDouble(TurretPreferences.pid_F, default_pid_F);
        logger.info("{} = {}", TurretPreferences.pid_F, v);
        pid_F = v;
    }

    // Current speed of motor
    private double tlmSpeed = 0.0;
    // Setting for speed of motor (target or direct)
    private double tlmSetSpeed = 0.0;

    protected void setTlmSpeed(double speed) {
        tlmSpeed = speed;
    }

    protected void setTlmSetSpeed(double speed) {
        tlmSetSpeed = speed;
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putNumber(TelemetryNames.Turret.speed, tlmSpeed);
        SmartDashboard.putNumber(TelemetryNames.Turret.setSpeed, tlmSetSpeed);
    }

    @Override
    public void updatePreferences() {
        loadPreferences();
    }

}
