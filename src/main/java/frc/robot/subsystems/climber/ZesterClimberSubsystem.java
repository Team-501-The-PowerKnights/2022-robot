/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.climber;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;

public class ZesterClimberSubsystem extends BaseClimberSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ZesterClimberSubsystem.class.getName());

    private final CANSparkMax motor;

    private final AnalogInput limitUp;
    private final AnalogInput limitDown;

    // Keep for telemetry
    private double tlmSpeed;

    ZesterClimberSubsystem() {
        logger.info("constructing");

        motor = new CANSparkMax(55, MotorType.kBrushless);
        motor.restoreFactoryDefaults();
        motor.setIdleMode(IdleMode.kBrake);

        limitUp = new AnalogInput(0);
        limitDown = new AnalogInput(1);

        tlmSpeed = 0.0;

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putBoolean(TelemetryNames.Climber.topLimit, (limitUp.getValue() == 1));
        SmartDashboard.putBoolean(TelemetryNames.Climber.bottomLimit, (limitDown.getValue() == 1));
        SmartDashboard.putNumber(TelemetryNames.Climber.speed, tlmSpeed);
    }

    @Override
    public void validateCalibration() {
        // TODO Auto-generated method stub

    }

    @Override
    public void updatePreferences() {
        // No preferences for climber
    }

    @Override
    public void disable() {
        // TODO Auto-generated method stub

    }

    @Override
    public void stop() {
        motor.set(0.0);
    }

    @Override
    public void extend() {
        setSpeed(0.40);
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
        tlmSpeed = speed;
        motor.set(tlmSpeed);
    }

}
