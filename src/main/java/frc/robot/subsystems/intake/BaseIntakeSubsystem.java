/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.intake;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.intake.IntakeDoNothing;
import frc.robot.subsystems.BaseSubsystem;
import frc.robot.subsystems.SubsystemNames;
import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Add your docs here.
 */
abstract class BaseIntakeSubsystem extends BaseSubsystem implements IIntakeSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(BaseIntakeSubsystem.class.getName());

    BaseIntakeSubsystem() {
        super(SubsystemNames.intakeName);
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public void loadDefaultCommands() {
        loadDefaultCommands(IntakeDoNothing.class);
    }

    protected void loadPreferences() {
        @SuppressWarnings("unused")
        double v;

        logger.info("new preferences for {}:", myName);
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

    @Override
    public void updatePreferences() {
        loadPreferences();
    }

}
