/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.intake;


import com.revrobotics.CANSparkMax;
import com.revrobotics.REVLibError;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.modules.pcm.IPCMModule;
import frc.robot.modules.pcm.PCMFactory;

import riolog.PKLogger;
import riolog.RioLogger;


class IntakeSubsystem extends BaseIntakeSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(IntakeSubsystem.class.getName());

    // Our motor to drive the intake in (up) & out (down)
    //private final TalonSRX motor;
    private final CANSparkMax motor;

    // Our pneumatics to extend/retract the intake
    private final IPCMModule pcm;

    /**
     * Creates a new IntakeSubsystem.
     * @param IdleMode 
     */
    IntakeSubsystem() {
        logger.info("constructing");

        // motor = new TalonSRX(41);
        // motor.setInverted(false);
        // motor.configFactoryDefault();

        lastError = REVLibError.kOk;

        motor = new CANSparkMax(41, MotorType.kBrushless);
        checkError(motor.restoreFactoryDefaults(), "setting factory defaults {}");

        checkError(motor.setIdleMode(IdleMode.kBrake), "setting to brake {}");

        motor.setInverted(true);


        pcm = PCMFactory.getInstance();

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
    }

    @Override
    public void validateCalibration() {
        // Zester doesn't implement this
    }

    @Override
    public void updatePreferences() {
        super.updatePreferences();

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
        setSpeed(1.0);
    }

    @Override
    public void pullIn(double speed) {
        super.pullIn(speed);

        setSpeed(speed);
    }

    @Override
    public void pushOut() {
        setSpeed(-0.8);
    }

    @Override
    public void pushOut(double speed) {
        super.pushOut(speed);

        setSpeed(speed);

    }

    private void setSpeed(double speed) {
        setTlmSpeed(speed);

        motor.set(speed);
    }

    @Override
    public void retract() {
        super.retract();

        pcm.retractIntake();
    }

    @Override
    public void extend() {
        super.extend();

        pcm.extendIntake();
    }

}
