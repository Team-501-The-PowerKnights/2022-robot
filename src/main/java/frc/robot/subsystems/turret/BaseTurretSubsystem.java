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

import frc.robot.commands.turret.TurretDoNothing;
import frc.robot.subsystems.BaseSubsystem;
import frc.robot.subsystems.SubsystemNames;
import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Add your docs here.
 */
abstract class BaseTurretSubsystem extends BaseSubsystem implements ITurretSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(BaseTurretSubsystem.class.getName());

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
        super(SubsystemNames.turretName);
        logger.info("constructing");

        logger.info("constructed");
    }

    @Override
    public void loadDefaultCommands() {
        loadDefaultCommands(TurretDoNothing.class);
        SmartDashboard.putString(TelemetryNames.Turret.autoCommand, defaultAutoCommand.getClass().getSimpleName());
        SmartDashboard.putString(TelemetryNames.Turret.teleCommand, defaultTeleCommand.getClass().getSimpleName());
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
