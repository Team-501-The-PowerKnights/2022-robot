/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.climber;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//import edu.wpi.first.wpilibj.AnalogInput;

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

        motor = new CANSparkMax(55, MotorType.kBrushless);
        motor.restoreFactoryDefaults();
        motor.setIdleMode(IdleMode.kBrake);
        motor.setOpenLoopRampRate(0.5); // 1.0
        motor.setSmartCurrentLimit(45);

        encoder = motor.getEncoder();
        encoder.setPosition(0.0);

        // limitUp = new AnalogInput(0);
        // limitDown = new AnalogInput(1);

        logger.info("constructed");

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

        setSpeed(-0.20);
    }

    private void setSpeed(double speed) {
        setTlmSpeed(speed);
        motor.set(speed);
    }

    @Override
    public double getPosition() {
        return encoder.getPosition();
    }

}
