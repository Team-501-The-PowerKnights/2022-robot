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

    @Override
    public void loadDefaultCommand() {
        PKProperties props = PropertiesManager.getInstance().getProperties(myName);
        String myClassName = props.getString("defaultCommandName");
        String myPkgName = ElevatorDoNothing.class.getPackage().getName();
        String classToLoad = new StringBuilder().append(myPkgName).append(".").append(myClassName).toString();
        logger.debug("class to load: {}", classToLoad);

        logger.info("constructing {} for {} subsystem", myClassName, myName);
        Command ourCommand;
        try {
            @SuppressWarnings("rawtypes")
            Class myClass = Class.forName(classToLoad);
            @SuppressWarnings("deprecation")
            Object myObject = myClass.newInstance();
            ourCommand = (Command) myObject;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException ex) {
            logger.error("failed to load class; instantiating default stub for: {}", myName);
            ourCommand = (Command) new ElevatorDoNothing();
            SmartDashboard.putNumber(TelemetryNames.Elevator.status, PKStatus.degraded.tlmValue);
        }

        setDefaultCommand(ourCommand);
        SmartDashboard.putString(TelemetryNames.Elevator.defCommand, ourCommand.getClass().getSimpleName());
    }

    private double tlmSpeed = 0.0;
    private boolean tlmStopped = false;
    private boolean tlmLifting = false;
    private boolean tlmLowering = false;

    @Override
    public void updateTelemetry()
    {
        SmartDashboard.putNumber(TelemetryNames.Elevator.speed, tlmSpeed);
        SmartDashboard.putBoolean(TelemetryNames.Elevator.stopped, tlmStopped);
        SmartDashboard.putBoolean(TelemetryNames.Elevator.lifting, tlmLifting);
        SmartDashboard.putBoolean(TelemetryNames.Elevator.lowering, tlmLowering);
    }

    protected void setTlmSpeed(double speed)
    {
        tlmSpeed = speed;
    }

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

}
