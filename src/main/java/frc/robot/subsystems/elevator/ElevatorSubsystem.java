/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.elevator;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// import frc.robot.sensors.elevator.ElevatorSensorFactory;
// import frc.robot.sensors.elevator.IElevatorSensor;
import frc.robot.sensors.incrementer.IIncrementerSensor;
import frc.robot.sensors.incrementer.IncrementerSensorFactory;
// import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;


class ElevatorSubsystem extends BaseElevatorSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ElevatorSubsystem.class.getName());

    private final VictorSPX motor;

    // private final IElevatorSensor elevatorSensor;
    private final IIncrementerSensor incremSensor;

    ElevatorSubsystem() {
        logger.info("constructing");

        motor = new VictorSPX(50);
        motor.configFactoryDefault();
        motor.setInverted(true);

        // elevatorSensor = ElevatorSensorFactory.getInstance();
        incremSensor = IncrementerSensorFactory.getInstance();

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        super.updateTelemetry();

        // SmartDashboard.putBoolean(TelemetryNames.Elevator.atLimit,
        // elevatorSensor.get());
    }

    @Override
    public void validateCalibration() {
        // Stub doesn't implement this
    }

    @Override
    public void updatePreferences() {
        super.updatePreferences();

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

        setSpeed(-1.0);
    }

    @Override
    public void lower() {
        super.lower();

        setSpeed(1.0);
    }

    @Override
    public boolean isFull() {
        // return (elevatorSensor.get() && incremSensor.get());
        return incremSensor.get();
    }

    @Override
    public void liftToLimit() {
        super.liftToLimit();

        // FIXME: Needs oneshot for speed controller set
        if (!isFull()) {
            lift();
        } else {
            stop();
        }
    }

    private void setSpeed(double speed) {
        setTlmSpeed(speed);

        motor.set(ControlMode.PercentOutput, speed);
    }

}
