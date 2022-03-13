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

    private final CANSparkMax motor;
    private final RelativeEncoder encoder;

    // private final AnalogInput limitUp;
    // private final AnalogInput limitDown;

    ClimberSubsystem() {
        logger.info("constructing");

        lastError = REVLibError.kOk;

        motor = new CANSparkMax(55, MotorType.kBrushless);
        checkError(motor.restoreFactoryDefaults(), "setting factory defaults {}");

        checkError(motor.setIdleMode(IdleMode.kBrake), "setting to brake {}");
        checkError(motor.setOpenLoopRampRate(0.5), "setting ramp rate {}");
        checkError(motor.setSmartCurrentLimit(35), "setting current limit {}");

        encoder = motor.getEncoder();

        checkError(encoder.setPosition(0.0), "zeroing the encoder {}");
 
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

        setSpeed(0.0);
    }

    @Override
    public void climb() {
        super.climb();

        setSpeed(1.0);
    }

    @Override
    public void retract() {
        super.retract();

        setSpeed(-0.40);
    }

    private void setSpeed(double speed) {
        setTlmSpeed(speed);
        motor.set(speed);
    }

    @Override
    public double getPosition() {
        return encoder.getPosition();
    }

    @Override
    public void zeroPosition() {
        encoder.setPosition(0.0);
    }

}
