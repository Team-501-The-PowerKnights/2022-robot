/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.elevator;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;


class ElevatorSubsystem extends BaseElevatorSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ElevatorSubsystem.class.getName());

    private final CANSparkMax motor;
    private final CANSparkMax incrementer;

    private final DigitalInput limit;

    ElevatorSubsystem() {
        logger.info("constructing");

        motor = new CANSparkMax(51, MotorType.kBrushless);
        motor.restoreFactoryDefaults();
        motor.setIdleMode(IdleMode.kBrake);

        incrementer = new CANSparkMax(52, MotorType.kBrushless);
        incrementer.restoreFactoryDefaults();
        incrementer.setIdleMode(IdleMode.kBrake);

        limit = new DigitalInput(9);

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        super.updateTelemetry();

        SmartDashboard.putBoolean(TelemetryNames.Elevator.atLimit, limit.get());
    }

    @Override
    public void validateCalibration() {
        // Stub doesn't implement this
    }

    @Override
    public void updatePreferences() {
        // Stub doesn't implement this
    }

    @Override
    public void disable() {
        // Stub doesn't implement this
    }

    @Override
    public void stop() {
        super.stop();

        setSpeed(0.0);
    }

    @Override
    public void lift() {
        super.lift();

        setSpeed(-0.85);
    }

    @Override
    public void lower() {
        super.lower();

        setSpeed(1.0);
    }

    @Override
    public void increment() {
        incrementer.set(0.4);
    }

    @Override
    public void stopIncrement() {
        incrementer.set(0.0);
    }

    @Override
    public boolean isFull() {
        return limit.get();
    }

    @Override
    public void liftToLimit() {
        super.liftToLimit();

        if (!isFull()) {
            lift();
        } else {
            stop();
        }
    }

    private void setSpeed(double speed) {
        setTlmSpeed(speed);

        motor.set(speed);
    }

}
