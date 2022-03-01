/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.intake;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import frc.robot.commands.intake.IntakeDoNothing;
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
abstract class BaseIntakeSubsystem extends SubsystemBase implements IIntakeSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(BaseIntakeSubsystem.class.getName());

    /** Our subsystem's name **/
    protected static final String myName = SubsystemNames.intakeName;

    BaseIntakeSubsystem() {
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public void loadDefaultCommand() {
        PKProperties props = PropertiesManager.getInstance().getProperties(myName);
        String myClassName = props.getString("defaultCommandName");
        if (myClassName.isEmpty()) {
            logger.info("no class specified; go with subsystem default (do nothing)");
            myClassName = new StringBuilder().append(myName).append("DoNothing").toString();
        }
        String myPkgName = IntakeDoNothing.class.getPackage().getName();
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
            ourCommand = (Command) new IntakeDoNothing();
            SmartDashboard.putNumber(TelemetryNames.Intake.status, PKStatus.degraded.tlmValue);
        }

        setDefaultCommand(ourCommand);
        SmartDashboard.putString(TelemetryNames.Intake.defCommand, ourCommand.getClass().getSimpleName());
    }

    private double tlmSpeed = 0.0;
    private boolean tlmStopped = false;
    private boolean tlmPullingIn = false;
    private boolean tlmPushingOut = false;
    private boolean tlmExtended = false;
    private boolean tlmRetracted = false;

    @Override
    public void updateTelemetry() {
        SmartDashboard.putNumber(TelemetryNames.Intake.speed, tlmSpeed);
        SmartDashboard.putBoolean(TelemetryNames.Intake.stopped, tlmStopped);
        SmartDashboard.putBoolean(TelemetryNames.Intake.pullingIn, tlmPullingIn);
        SmartDashboard.putBoolean(TelemetryNames.Intake.pushingOut, tlmPushingOut);
        SmartDashboard.putBoolean(TelemetryNames.Intake.extended, tlmExtended);
        SmartDashboard.putBoolean(TelemetryNames.Intake.retracted, tlmRetracted);
    }

    protected void setTlmSpeed(double speed) {
        tlmSpeed = speed;
    }

    @Override
    public void stop() {
        tlmStopped = true;
        tlmPullingIn = false;
        tlmPushingOut = false;
    }

    @Override
    public void pullIn(double speed) {
        tlmStopped = false;
        tlmPullingIn = true;
        tlmPushingOut = false;
    }

    @Override
    public void pushOut(double speed) {
        tlmStopped = false;
        tlmPullingIn = false;
        tlmPushingOut = true;
    }

    @Override
    public void retract() {

    }

    @Override
    public void extend() {

    }

}
