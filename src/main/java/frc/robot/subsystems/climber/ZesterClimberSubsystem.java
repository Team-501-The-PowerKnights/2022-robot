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
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;


class ZesterClimberSubsystem extends BaseClimberSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ZesterClimberSubsystem.class.getName());

    private final CANSparkMax motor;

    private final AnalogInput limitUp;
    private final AnalogInput limitDown;

    ZesterClimberSubsystem() {
        logger.info("constructing");

        lastError = REVLibError.kOk;

        motor = new CANSparkMax(55, MotorType.kBrushless);
        checkError(motor.restoreFactoryDefaults(), "setting factory defaults {}");

        checkError(motor.setIdleMode(IdleMode.kBrake), "setting to brake {}");

        limitUp = new AnalogInput(0);
        limitDown = new AnalogInput(1);

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

        SmartDashboard.putBoolean(TelemetryNames.Climber.topLimit, (limitUp.getValue() == 1));
        SmartDashboard.putBoolean(TelemetryNames.Climber.bottomLimit, (limitDown.getValue() == 1));
    }

    @Override
    public void validateCalibration() {
        // TODO Auto-generated method stub
    }

    @Override
    public void updatePreferences() {
        super.updatePreferences();

        // No preferences for climber
    }

    @Override
    public void disable() {
        // TODO Auto-generated method stub
    }

    @Override
    public void stop() {
        setSpeed(0.0);
    }

    @Override
    public void climb() {
        setSpeed(1.0);
    }

    @Override
    public void retract() {
        setSpeed(-0.20);
    }

    private void setSpeed(double speed) {
        setTlmSpeed(speed);
        ;
        motor.set(speed);
    }

    @Override
    public double getPosition() {
        // Zester doesn't implement this
        return 0;
    }

    @Override
    public void zeroPosition() {
        // TODO Auto-generated method stub

    }

}
