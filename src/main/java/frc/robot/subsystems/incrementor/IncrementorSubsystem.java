/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.incrementor;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.sensors.incrementor.IIncrementorLoadedSensor;
import frc.robot.sensors.incrementor.IncrementorLoadedSensorFactory;
import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;


class IncrementorSubsystem extends BaseIncrementorSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(IncrementorSubsystem.class.getName());

    private final TalonSRX motor;

    private final IIncrementorLoadedSensor sensor;

    IncrementorSubsystem() {
        logger.info("constructing");

        motor = new TalonSRX(51);
        motor.configFactoryDefault();

        sensor = IncrementorLoadedSensorFactory.getInstance();

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        super.updateTelemetry();

        SmartDashboard.putBoolean(TelemetryNames.Incrementer.atLimit, sensor.get());
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
    public void increment() {
        setSpeed(1.0);
    }

    @Override
    public boolean isFull() {
        return sensor.get();
    }

    @Override
    public void incrementToLimit() {
        super.incrementToLimit();

        // FIXME: Needs oneshot for speed controller set
        if (!isFull()) {
            increment();
        } else {
            stop();
        }
    }

    private void setSpeed(double speed) {
        setTlmSpeed(speed);

        motor.set(ControlMode.PercentOutput, speed);
    }

}
