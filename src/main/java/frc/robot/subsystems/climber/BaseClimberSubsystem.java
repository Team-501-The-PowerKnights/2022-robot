/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.climber;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.climber.ClimberDoNothing;
import frc.robot.subsystems.BaseSubsystem;
import frc.robot.subsystems.SubsystemNames;
import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;

/**
 * Add your docs here.
 */
abstract class BaseClimberSubsystem extends BaseSubsystem implements IClimberSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(BaseClimberSubsystem.class.getName());

    BaseClimberSubsystem() {
        super(SubsystemNames.climberName);
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public void loadDefaultCommands() {
        loadDefaultCommands(ClimberDoNothing.class);
        SmartDashboard.putString(TelemetryNames.Climber.autoCommand, defaultAutoCommand.getClass().getSimpleName());
        SmartDashboard.putString(TelemetryNames.Climber.teleCommand, defaultTeleCommand.getClass().getSimpleName());
    }

    protected void loadPreferences() {
        @SuppressWarnings("unused")
        double v;

        logger.info("new preferences for {}:", myName);
    }

    private double tlmSpeed = 0.0;
    private boolean tlmClimbing = false;
    private boolean tlmRetracting = false;

    protected void setTlmSpeed(double speed) {
        tlmSpeed = speed;
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putNumber(TelemetryNames.Climber.leftPosition, getLeftPosition());
        SmartDashboard.putNumber(TelemetryNames.Climber.rightPosition, getRightPosition());
        SmartDashboard.putNumber(TelemetryNames.Climber.position, getAveragePosition());
        SmartDashboard.putNumber(TelemetryNames.Climber.speed, tlmSpeed);
        SmartDashboard.putBoolean(TelemetryNames.Climber.climbing, tlmClimbing);
        SmartDashboard.putBoolean(TelemetryNames.Climber.retracting, tlmRetracting);
    }

    @Override
    public void updatePreferences() {
        loadPreferences();
    }

    @Override
    public void stop() {
        tlmClimbing = false;
        tlmRetracting = false;
    }

}
