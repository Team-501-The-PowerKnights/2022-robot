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

    private final CANSparkMax shoulderMotor;
    private final CANSparkMax elbowMotor;

    private final RelativeEncoder shoulderEncoder;
    private final RelativeEncoder elbowEncoder;

    // private final AnalogInput limitUp;
    // private final AnalogInput limitDown;

    ClimberSubsystem() {
        logger.info("constructing");

        lastError = REVLibError.kOk;

        shoulderMotor = new CANSparkMax(55, MotorType.kBrushless);
        checkError(shoulderMotor.restoreFactoryDefaults(), "setting factory defaults {}");

        checkError(shoulderMotor.setIdleMode(IdleMode.kBrake), "setting to brake {}");
        checkError(shoulderMotor.setOpenLoopRampRate(0.5), "setting ramp rate {}");
        checkError(shoulderMotor.setSmartCurrentLimit(35), "setting current limit {}");

        shoulderEncoder = shoulderMotor.getEncoder();

        checkError(shoulderEncoder.setPosition(0.0), "zeroing the shoulderEncoder {}");

        elbowMotor = new CANSparkMax(56, MotorType.kBrushless);
        checkError(elbowMotor.restoreFactoryDefaults(), "setting factory defaults {}");

        checkError(elbowMotor.setIdleMode(IdleMode.kBrake), "setting to brake {}");
        checkError(elbowMotor.setOpenLoopRampRate(0.5), "setting ramp rate {}");
        checkError(elbowMotor.setSmartCurrentLimit(35), "setting current limit {}");

        elbowEncoder = elbowMotor.getEncoder();

        checkError(elbowEncoder.setPosition(0.0), "zeroing the elbowEncoder {}");

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
    public void runElbow(double speed) {

    }

    @Override
    public void runShoulder(double speed) {
        // TODO Auto-generated method stub

    }

    @Override
    public void elbowGoToSetPoint(double setPoint) {
        // TODO Auto-generated method stub

    }

    @Override
    public void shoulderGoToSetPoint(double setPoint) {
        // TODO Auto-generated method stub

    }

    private void setSpeed(double speed, int motorId) {
        setTlmSpeed(speed);
        switch (motorId) {
            case 55:
                shoulderMotor.set(speed);
            case 56:
                elbowMotor.set(speed);
            default:
                break;
        }
    }

    @Override
    public double getShoulderPosition() {
        return shoulderEncoder.getPosition();
    }

    @Override
    public void zeroShoulderPosition() {
        shoulderEncoder.setPosition(0.0);
    }

    @Override
    public double getElbowPosition() {
        return elbowEncoder.getPosition();
    }

    @Override
    public void zeroElbowPosition() {
        elbowEncoder.setPosition(0.0);
    }

}
