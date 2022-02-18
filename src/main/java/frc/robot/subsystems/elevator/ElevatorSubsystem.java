/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.telemetry.TelemetryNames;
import riolog.PKLogger;
import riolog.RioLogger;

public class ElevatorSubsystem extends BaseElevatorSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ElevatorSubsystem.class.getName());

    private final TalonSRX motor;

    private final DigitalInput limit;

    // Keep for telemetry
    private double tlmSpeed;

    ElevatorSubsystem() {
        logger.info("constructing");

        motor = new TalonSRX(51);
        motor.configFactoryDefault();
        motor.setNeutralMode(NeutralMode.Brake);

        limit = new DigitalInput(9);

        tlmSpeed = 0.0;

        logger.info("constructed");
    }

    @Override
    public void stop() {
        motor.set(ControlMode.PercentOutput, 0);
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
    public void updateTelemetry() {
        SmartDashboard.putNumber(TelemetryNames.Elevator.speed, tlmSpeed);
        SmartDashboard.putBoolean(TelemetryNames.Elevator.atLimit, limit.get());
    }

    @Override
    public void lift() {
        setSpeed(-0.85);
    }

    @Override
    public void lower() {
        setSpeed(1.0);
    }

    @Override
    public boolean isFull() {
        return limit.get();
    }

    private void setSpeed(double speed) {
        tlmSpeed = speed;
        motor.set(ControlMode.PercentOutput, tlmSpeed);
    }

    @Override
    public void liftToLimit() {
        if (!isFull()) {
            lift();
        } else {
            stop();
        }
    }

}
