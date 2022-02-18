/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/


package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import riolog.PKLogger;
import riolog.RioLogger;


public class ZesterIntakeSubsystem extends BaseIntakeSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ZesterIntakeSubsystem.class.getName());

    // Our motor to drive the intake in (up) & out (down)
    private final TalonSRX motor;

    /**
     * Creates a new IntakeSubsystem.
     */
    ZesterIntakeSubsystem() {
        logger.info("constructing");

        motor = new TalonSRX(41);
        motor.configFactoryDefault();

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        super.updateTelemetry();
    }

    @Override
    public void validateCalibration() {
        // Zester doesn't implement this
    }

    @Override
    public void updatePreferences() {
        // Zester doesn't implement this
    }

    @Override
    public void disable() {
        stop();
    }

    @Override
    public void stop() {
        super.stop();

        setSpeed(0.0);
    }

    @Override
    public void pullIn() {
        pullIn(1.0);
    }

    @Override
    public void pullIn(double speed) {
        super.pullIn(speed);

        setSpeed(speed);
    }

    @Override
    public void pushOut() {
        pushOut(-0.2);
    }

    @Override
    public void pushOut(double speed) {
        super.pushOut(speed);

        setSpeed(speed);
    }

    private void setSpeed(double speed) {
        super.setTlmSpeed(speed);

        motor.set(ControlMode.PercentOutput, tlmSpeed);
    }

}
