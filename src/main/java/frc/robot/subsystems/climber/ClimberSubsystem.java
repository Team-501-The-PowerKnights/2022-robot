/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.climber;

import com.revrobotics.CANSparkMax;
import com.revrobotics.REVLibError;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;

class ClimberSubsystem extends BaseClimberSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ClimberSubsystem.class.getName());

    private final CANSparkMax rightMotor;
    private final CANSparkMax leftMotor;

    private final RelativeEncoder rightEncoder;
    private final RelativeEncoder leftEncoder;

    // private final AnalogInput limitUp;
    // private final AnalogInput limitDown;

    ClimberSubsystem() {
        logger.info("constructing");

        lastError = REVLibError.kOk;

        rightMotor = new CANSparkMax(55, MotorType.kBrushless);
        checkError(rightMotor.restoreFactoryDefaults(), "setting factory defaults {}");

        checkError(rightMotor.setIdleMode(IdleMode.kBrake), "setting to brake {}");
        checkError(rightMotor.setOpenLoopRampRate(0.5), "setting ramp rate {}");
        checkError(rightMotor.setSmartCurrentLimit(35), "setting current limit {}");

        rightEncoder = rightMotor.getEncoder();

        checkError(rightEncoder.setPosition(0.0), "zeroing the rightEncoder {}");

        leftMotor = new CANSparkMax(56, MotorType.kBrushless);
        leftMotor.setInverted(true);
        checkError(leftMotor.restoreFactoryDefaults(), "setting factory defaults {}");

        checkError(leftMotor.setIdleMode(IdleMode.kBrake), "setting to brake {}");
        checkError(leftMotor.setOpenLoopRampRate(0.5), "setting ramp rate {}");
        checkError(leftMotor.setSmartCurrentLimit(35), "setting current limit {}");

        leftEncoder = leftMotor.getEncoder();

        checkError(leftEncoder.setPosition(0.0), "zeroing the leftEncoder {}");

        // limitUp = new AnalogInput(0);
        // limitDown = new AnalogInput(1);

        // TODO: Put these into subsystem?
        SmartDashboard.putNumber(TelemetryNames.Climber.targetPos, 0);
        SmartDashboard.putBoolean(TelemetryNames.Climber.atTarget, false);

        logger.info("constructed");
    }

    // last error (not the same as kOk)
    // TODO: Use to set a degraded error status/state on subsystem
    @SuppressWarnings("unused")
    private REVLibError lastError;

    private void checkError(REVLibError error, String message) {
        if (error != REVLibError.kOk) {
            lastError = error;
            logger.error(message, error);
        }
    }

    @Override
    public void updateTelemetry() {
        super.updateTelemetry();
        // SmartDashboard.putBoolean(TelemetryNames.Climber.topLimit,
        // (limitUp.getValue() == 1));
        // SmartDashboard.putBoolean(TelemetryNames.Climber.bottomLimit,
        // (limitDown.getValue() == 1));
        // SmartDashboard.putBoolean(TelemetryNames.Climber.topLimit, false);
        // SmartDashboard.putBoolean(TelemetryNames.Climber.bottomLimit, false);
    }

    @Override
    public void validateCalibration() {
        // Real doesn't implement this
    }

    @Override
    public void updatePreferences() {
        super.updatePreferences();

        // Real doesn't implement this
    }

    @Override
    public void disable() {
        // Real doesn't implement this
    }

    @Override
    public void stop() {
        super.stop();

        setSpeed(0.0, 55);
        setSpeed(0.0, 56);
    }

    @Override
    public void run(double speed) {
        setSpeed(speed, 55);
        setSpeed(speed, 56);
    }

    @Override
    public void goToSetPoint(double setPoint) {
        // PID control not implemented yet
    }

    private void setSpeed(double speed, int motorId) {
        setTlmSpeed(speed);
        switch (motorId) {
            case 55:
                rightMotor.set(speed);
            case 56:
                leftMotor.set(speed);
            default:
                break;
        }
    }

    @Override
    public void zeroPosition() {
        rightEncoder.setPosition(0.0);
        leftEncoder.setPosition(0.0);
    }

    @Override
    public double getRightPosition() {
        return rightEncoder.getPosition();
    }

    @Override
    public double getLeftPosition() {
        return leftEncoder.getPosition();
    }

    @Override
    public double getAveragePosition() {
        return (getLeftPosition() + getRightPosition()) / 2;
    }

}
