/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.elevator;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.commands.elevator.ElevatorDoNothing;
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
abstract class BaseElevatorSubsystem extends SubsystemBase implements IElevatorSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(BaseElevatorSubsystem.class.getName());

    /** Our subsystem's name **/
    protected static final String myName = SubsystemNames.elevatorName;

    BaseElevatorSubsystem() {
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
        String myPkgName = ElevatorDoNothing.class.getPackage().getName();
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
            ourAutoCommand = (Command) new ElevatorDoNothing();
            SmartDashboard.putNumber(TelemetryNames.Elevator.status, PKStatus.degraded.tlmValue);
        }

        defaultAutoCommand = ourAutoCommand;
        SmartDashboard.putString(TelemetryNames.Elevator.autoCommand, ourAutoCommand.getClass().getSimpleName());

        String myTeleClassName = props.getString("teleCommandName");
        if (myTeleClassName.isEmpty()) {
            logger.info("no class specified; go with subsystem default (do nothing)");
            myTeleClassName = new StringBuilder().append(myName).append("DoNothing").toString();
        }
        myPkgName = ElevatorDoNothing.class.getPackage().getName();
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
            ourTeleCommand = (Command) new ElevatorDoNothing();
            SmartDashboard.putNumber(TelemetryNames.Elevator.status, PKStatus.degraded.tlmValue);
        }

        defaultTeleCommand = ourTeleCommand;
        SmartDashboard.putString(TelemetryNames.Elevator.teleCommand, ourTeleCommand.getClass().getSimpleName());
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
    private boolean tlmStopped = false;
    private boolean tlmLifting = false;
    private boolean tlmLowering = false;
    private boolean tlmFull = false;

    @Override
    public void updateTelemetry() {
        SmartDashboard.putNumber(TelemetryNames.Elevator.speed, tlmSpeed);
        SmartDashboard.putBoolean(TelemetryNames.Elevator.stopped, tlmStopped);
        SmartDashboard.putBoolean(TelemetryNames.Elevator.lifting, tlmLifting);
        SmartDashboard.putBoolean(TelemetryNames.Elevator.lowering, tlmLowering);
        tlmFull = isFull();
        SmartDashboard.putBoolean(TelemetryNames.Elevator.full, tlmFull);
    }

    protected void setTlmSpeed(double speed) {
        tlmSpeed = speed;
    }

    // FIXME: Make these methods to just set telemetry so not calling "super."

    @Override
    public void stop() {
        tlmStopped = true;
        tlmLifting = false;
        tlmLowering = false;
    }

    @Override
    public void lift() {
        tlmStopped = false;
        tlmLifting = true;
        tlmLowering = false;
    }

    @Override
    public void lower() {
        tlmStopped = false;
        tlmLifting = false;
        tlmLowering = true;
    }

    @Override
    public void liftToLimit() {
        tlmStopped = false;
        tlmLifting = true;
        tlmLowering = false;
    }

    @Override
    public void updatePreferences() {
        loadPreferences();
    }

}
