/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.shooter;

import com.revrobotics.CANSparkMax;
import com.revrobotics.REVLibError;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.SoftLimitDirection;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.commands.CommandingNames;
import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;

class ShooterSubsystem extends BaseShooterSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ShooterSubsystem.class.getName());

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
    ShooterSubsystem() {
        logger.info("constructing");

        leftMotor = new CANSparkMax(21, MotorType.kBrushless);
        if (leftMotor.restoreFactoryDefaults() == REVLibError.kOk) {
            logger.info("Left factory defaults restored successfully");
        } else {
            logger.warn("An error occurred setting left factory defaults");
        }
        rightMotor = new CANSparkMax(22, MotorType.kBrushless);
        if (rightMotor.restoreFactoryDefaults() == REVLibError.kOk) {
            logger.info("Right factory defaults restored successfully");
        } else {
            logger.warn("An error occurred setting right factory defaults");
        }
        // Voltage compensation
        if (leftMotor.enableVoltageCompensation(11.0) == REVLibError.kOk) {
            logger.info("Left voltage limit set successfully");
        } else {
            logger.warn("An error occurred setting left voltage limit");
        }
        if (rightMotor.enableVoltageCompensation(11.0) == REVLibError.kOk) {
            logger.info("Right voltage limit set successfully");
        } else {
            logger.warn("An error occurred setting right voltage limit");
        }

        // + spin out, - spin in
        leftMotor.setInverted(true);

        // Slaved and inverted
        if (rightMotor.follow(leftMotor, true) == REVLibError.kOk) {
            logger.info("Right successfully following left");
        } else {
            logger.warn("An error occurred setting right to follow left");
        }

        if (leftMotor.setSoftLimit(SoftLimitDirection.kReverse, 0.0f) == REVLibError.kOk) {
            logger.info("Left directional limit set successfully");
        } else {
            logger.warn("An error occurred setting left directional limit");
        }
        if (rightMotor.setSoftLimit(SoftLimitDirection.kReverse, 0.0f) == REVLibError.kOk) {
            logger.info("Right directional limit set successfully");
        } else {
            logger.warn("An error occurred setting right directional limit");
        }

        encoder = leftMotor.getEncoder();

        pid = leftMotor.getPIDController();
        if (pid.setOutputRange(0.05, 1, slotID) == REVLibError.kOk) {
            logger.info("PID output range set successfully");
        } else {
            logger.warn("An error occurred setting PID output range");
        }

        targetRpm = 2000; // TODO - Make the values
        isActive = false;

        // TODO: Is this really used anywhere? for anything?
        SmartDashboard.putNumber(CommandingNames.Shooter.tolerance, 0.012);

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        setTlmSpeed(leftMotor.get());
        super.updateTelemetry();

        SmartDashboard.putBoolean(TelemetryNames.Shooter.isActive, isActive);
        SmartDashboard.putNumber(TelemetryNames.Shooter.rpm, encoder.getVelocity());
        SmartDashboard.putNumber(TelemetryNames.Shooter.targetRpm, targetRpm);
        SmartDashboard.putBoolean(TelemetryNames.Shooter.atTarget, atTargetVelocity());
    }

    @Override
    public void validateCalibration() {
        // Nothing here
    }

    @Override
    public void updatePreferences() {
        super.updatePreferences();

        // Nothing extra here.
    }

    @Override
    public void disable() {
        logger.info("last value of RPM tolerance: {}", tolerance);
    }

    @Override
    public void stop() {
        setSpeed(0.0);

        isActive = false;
    }

    @Override
    public void setTargetRpm(double rpm) {
        this.targetRpm = rpm; // Save off value for enabling

        if (isActive) {
            if (pid.setReference(targetRpm, CANSparkMax.ControlType.kVelocity, slotID) == REVLibError.kOk) {
                logger.info("PID setpoint set successfully to: {}", targetRpm);
            } else {
                logger.warn("An error occurred setting PID setpoint");
            }
        }
    }

    @Override
    public void shoot() {
        isActive = true;
        /* generated speed */
        if (pid.setReference(targetRpm, CANSparkMax.ControlType.kVelocity, slotID) == REVLibError.kOk) {
            logger.info("PID setpoint set successfully to: {}", targetRpm);
        } else {
            logger.warn("An error occurred setting PID setpoint");
        }
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
                setSpeed(idleShooter(speed));
                break;
            default:
                break;
        }
    }

    private double idleShooter(double speed) {
        // Have to be connected to the field to idle
        // if (!OI.getInstance().isFieldConnected()) {
        // return 0.0;
        // }

        // Dashboard provides scale for shooter speed
        // double scale = Preferences.getDouble(Shooter.scale, 1.0);
        // speed *= scale;
        // speed = Math.min(0.20, speed);
        // FIXME: Need to get commands to handle idle and spin up again this year
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

    private void setSpeed(double speed) {
        setTlmSetSpeed(speed);

        leftMotor.set(speed);
    }

}
