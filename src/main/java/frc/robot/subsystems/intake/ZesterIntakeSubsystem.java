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

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.telemetry.TelemetryNames;
import riolog.PKLogger;
import riolog.RioLogger;

public class ZesterIntakeSubsystem extends BaseIntakeSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ZesterIntakeSubsystem.class.getName());

    // Our motor to drive the intake in (up) & out (down)
    private final TalonSRX motor;

    // Keep for telemetry
    private double tlmSpeed;

    /**
     * Creates a new IntakeSubsystem.
     */
    ZesterIntakeSubsystem() {
        logger.info("constructing");

        motor = new TalonSRX(41);
        motor.configFactoryDefault();

        tlmSpeed = 0.0;

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putNumber(TelemetryNames.Intake.speed, tlmSpeed);
    }

    @Override
    public void stop() {
        setSpeed(0.0);
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
    public void pullIn() {
        setSpeed(1.0);
    }

    @Override
    public void pullIn(double speed) {
        setSpeed(speed);
    }

    @Override
    public void pushOut() {
        setSpeed(-0.2);
    }

    @Override
    public void pushOut(double speed) {
        setSpeed(speed);

    }

    private void setSpeed(double speed) {
        tlmSpeed = speed;
        motor.set(ControlMode.PercentOutput, tlmSpeed);
    }

}
