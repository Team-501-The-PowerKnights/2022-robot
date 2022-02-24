/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.shooter;


import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.OI;
import frc.robot.commands.CommandingNames;
import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;


class ZesterShooterSubsystem extends BaseShooterSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ZesterShooterSubsystem.class.getName());

    //
    private static final int slotID = 1;

    // Left motor (master)
    private CANSparkMax leftMotor;
    // Right motor
    private CANSparkMax rightMotor;

    // Encoder
    private RelativeEncoder encoder;
    // PID
    private SparkMaxPIDController pid;

    // Value of the RPM to use for speed
    private double targetRpm;
    // Value to compare RPM actual to target
    private double tolerance;

    // Flag for whether active (i.e., spinning)
    private boolean isActive;

    /**
     * Creates a new ShooterSubsystem.
     */
    ZesterShooterSubsystem() {
        logger.info("constructing");

        leftMotor = new CANSparkMax(21, MotorType.kBrushless);
        leftMotor.restoreFactoryDefaults();
        rightMotor = new CANSparkMax(22, MotorType.kBrushless);
        rightMotor.restoreFactoryDefaults();
        // + spin out, - spin in

        // Slaved and inverted
        rightMotor.follow(leftMotor, true);

        encoder = leftMotor.getEncoder();

        pid = leftMotor.getPIDController();
        pid.setOutputRange(0.05, 1, slotID);

        updatePreferences();

        targetRpm = 2000; // TODO - Make the values
        isActive = false;

        SmartDashboard.putNumber(CommandingNames.Shooter.tolerance, 0.012);

        logger.info("constructed");
    }

    @Override
    public void stop() {
        leftMotor.set(0.0);

        isActive = false;
    }

    @Override
    public void validateCalibration() {
        // Zester doesn't implement this
    }

    @Override
    public void updatePreferences() {
        SmartDashboard.putBoolean(TelemetryNames.Shooter.isActive, isActive);
        SmartDashboard.putNumber(TelemetryNames.Shooter.rpm, encoder.getVelocity());
        SmartDashboard.putNumber(TelemetryNames.Shooter.targetRpm, targetRpm);
        SmartDashboard.putBoolean(TelemetryNames.Shooter.atTarget, atTargetVelocity());
    }

    @Override
    public void disable() {
        logger.info("last value of RPM tolerance: {}", tolerance);
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putBoolean(TelemetryNames.Shooter.isActive, isActive);
        SmartDashboard.putNumber(TelemetryNames.Shooter.rpm, encoder.getVelocity());
        SmartDashboard.putNumber(TelemetryNames.Shooter.targetRpm, targetRpm);
        SmartDashboard.putBoolean(TelemetryNames.Shooter.atTarget, atTargetVelocity());
    }

    @Override
    public void setTargetRpm(double rpm) {
        this.targetRpm = rpm; // Save off value for enabling

        if (isActive) {
            pid.setReference(targetRpm, CANSparkMax.ControlType.kVelocity, slotID);
        }
    }

    @Override
    public void shoot() {
        isActive = true;
        /* generated speed */
        pid.setReference(targetRpm, CANSparkMax.ControlType.kVelocity, slotID);
    }

    // FIXME - Was supposed to be for manual; no idleShooter scaling
    @Override
    public void setSpeed(int canID, double speed) {
        switch (canID) {
            case 21:
                leftMotor.set(idleShooter(speed));
                break;
            case 22:
                rightMotor.set(idleShooter(speed));
                break;
            case 29:
                // Assuming slaved
                leftMotor.set(idleShooter(speed));
                break;
            default:
                break;
        }
    }

    private double idleShooter(double speed) {
        // Have to be connected to the field to idle
        if (!OI.getInstance().isFieldConnected()) {
            return 0.0;
        }

        // Dashboard provides scale for shooter speed
        // double scale = Preferences.getDouble(Shooter.scale, 1.0);
        // speed *= scale;
        speed = Math.min(0.20, speed);
        return speed;
    }

    @Override
    public boolean atTargetVelocity() {
        // FIXME - Find a way to only get a change via GUI
        tolerance = SmartDashboard.getNumber(CommandingNames.Shooter.tolerance, 0.012);

        return (((Math.abs(targetRpm - encoder.getVelocity())) / targetRpm) <= tolerance);

    }

    private String activePosition = "";

    @Override
    public String getActivePosition() {
        return activePosition;
    }

}
