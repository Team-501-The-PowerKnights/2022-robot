/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.shooter.ShooterDoNothing;
import frc.robot.subsystems.BaseSubsystem;
import frc.robot.subsystems.SubsystemNames;
import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;

/**
 * Add your docs here.
 */
abstract class BaseShooterSubsystem extends BaseSubsystem implements IShooterSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(BaseShooterSubsystem.class.getName());

    /** Default preferences for subystem **/
    private final double default_pid_P = 0.0;
    private final double default_pid_I = 0.0;
    private final double default_pid_D = 0.0;
    private final double default_pid_F = 0.0;
    private final double default_scale = 1.0;

    /** PID for subystem **/
    protected double pid_P = 0;
    protected double pid_I = 0;
    protected double pid_D = 0;
    protected double pid_F = 0;
    /** Scale applied to shooter speed when in manual */
    protected double scale = 1;

    // TODO: Make a preference like the PID values?
    // Default "idle" speed for shooter
    public final double defaultSetSpeed = 0.4665;

    BaseShooterSubsystem() {
        super(SubsystemNames.shooterName);
        logger.info("constructing");

        SmartDashboard.putNumber(TelemetryNames.Shooter.setSpeed, 0.5);

        logger.info("constructed");
    }

    @Override
    public void loadDefaultCommands() {
        loadDefaultCommands(ShooterDoNothing.class);
        SmartDashboard.putString(TelemetryNames.Shooter.autoCommand, defaultAutoCommand.getClass().getSimpleName());
        SmartDashboard.putString(TelemetryNames.Shooter.teleCommand, defaultTeleCommand.getClass().getSimpleName());
    }

    protected void loadPreferences() {
        double v;

        logger.info("new preferences for {}:", myName);
        v = Preferences.getDouble(ShooterPreferences.pid_P, default_pid_P);
        logger.info("{} = {}", ShooterPreferences.pid_P, v);
        pid_P = v;
        v = Preferences.getDouble(ShooterPreferences.pid_I, default_pid_I);
        logger.info("{} = {}", ShooterPreferences.pid_I, v);
        pid_I = v;
        v = Preferences.getDouble(ShooterPreferences.pid_D, default_pid_D);
        logger.info("{} = {}", ShooterPreferences.pid_D, v);
        pid_D = v;
        v = Preferences.getDouble(ShooterPreferences.pid_F, default_pid_F);
        logger.info("{} = {}", ShooterPreferences.pid_F, v);
        pid_F = v;
        v = Preferences.getDouble(ShooterPreferences.scale, default_scale);
        scale = v;
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
        SmartDashboard.putNumber(TelemetryNames.Shooter.speed, tlmSpeed);
        SmartDashboard.putNumber(TelemetryNames.Shooter.setSpeed, tlmSetSpeed);
    }

    @Override
    public void updatePreferences() {
        loadPreferences();
    }

}
