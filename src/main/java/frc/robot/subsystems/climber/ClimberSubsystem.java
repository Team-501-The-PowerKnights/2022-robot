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

public class ClimberSubsystem extends BaseClimberSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ClimberSubsystem.class.getName());

    private final CANSparkMax leftMotor;
    private final CANSparkMax rightMotor;

    private final AnalogInput limitUp;
    private final AnalogInput limitDown;

    // Keep for telemetry
    private double tlmSpeed;

    ClimberSubsystem() {
        logger.info("constructing");

        leftMotor = new CANSparkMax(56, MotorType.kBrushless);
        leftMotor.restoreFactoryDefaults();
        leftMotor.setIdleMode(IdleMode.kBrake);

        rightMotor = new CANSparkMax(55, MotorType.kBrushless);
        rightMotor.restoreFactoryDefaults();
        rightMotor.setIdleMode(IdleMode.kBrake);
        rightMotor.follow(leftMotor, true); // TODO - should this be inverted or not?

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
        // Real doesn't implement this

    }

    @Override
    public void updatePreferences() {
        // Real doesn't implement this
    }

    @Override
    public void disable() {
        // Real doesn't implement this

    }

    @Override
    public void stop() {
        leftMotor.set(0.0);
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
        leftMotor.set(tlmSpeed);
    }

}
