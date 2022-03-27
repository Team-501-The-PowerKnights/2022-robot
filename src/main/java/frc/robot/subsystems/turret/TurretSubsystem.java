/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.turret;


import com.revrobotics.CANSparkMax;
import com.revrobotics.REVLibError;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.sensors.turret.ITurretLocationSensor;
import frc.robot.sensors.turret.TurretLocationFactory;
import frc.robot.sensors.vision.IVisionSensor;
import frc.robot.sensors.vision.VisionFactory;
import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;


class TurretSubsystem extends BaseTurretSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(TurretSubsystem.class.getName());

    /**
     * Turret constant values
     */

    private static final double VPGearing = 100;
    private static final double beltGearing = 8;

    /**
     * Mechanisms and sensors
     */

    private final CANSparkMax motor;
    private final RelativeEncoder encoder;
    private final SparkMaxPIDController pid;
    private final ITurretLocationSensor location;

    private final IVisionSensor vision;

    TurretSubsystem() {
        logger.info("constructing");

        //
        motor = new CANSparkMax(20, MotorType.kBrushless);
        checkError(motor.restoreFactoryDefaults(), "setting factory defaults {}");

        // +CW +, CCW -
        motor.setInverted(true);

        checkError(motor.setSmartCurrentLimit(10), "setting current limit {}");

        //
        encoder = motor.getEncoder();

        checkError(encoder.setPosition(convertTurretAngleToCounts(-90)), "setting the encoder position {}");
 
        //
        pid = motor.getPIDController();

        checkError(pid.setIZone(0.25, 1), "PID setting IZone {}");
        checkError(pid.setIMaxAccum(1, 1), "PID setting IMaxAccum {}");
        checkError(pid.setP(pid_P, 1), "PID setting P {}");
        checkError(pid.setI(pid_I, 1), "PID setting I {}");
        checkError(pid.setD(pid_D, 1), "PID setting D {}");
        checkError(pid.setFF(pid_F, 1), "PID setting F {}");
        checkError(pid.setOutputRange(-1.0, 1.0, 1), "PID setting output range {}");
 
        location = TurretLocationFactory.getInstance();

        vision = VisionFactory.getInstance();

        SmartDashboard.putBoolean(TelemetryNames.Turret.isHomed, false);

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
        setTlmSpeed(motor.get()); // get current actual speed
        super.updateTelemetry();

        SmartDashboard.putNumber(TelemetryNames.Turret.angle, getAngle());
        SmartDashboard.putNumber(TelemetryNames.Turret.position, encoder.getPosition());
    }

    @Override
    public void validateCalibration() {
        // Nothing here
    }

    @Override
    public void updatePreferences() {
        super.updatePreferences();

        if (pid != null) {
            checkError(pid.setIZone(0.25, 1), "PID setting IZone {}");
            checkError(pid.setIMaxAccum(1, 1), "PID setting IMaxAccum {}");
            checkError(pid.setP(pid_P, 1), "PID setting P {}");
            checkError(pid.setI(pid_I, 1), "PID setting I {}");
            checkError(pid.setD(pid_D, 1), "PID setting D {}");
            checkError(pid.setFF(pid_F, 1), "PID setting F {}");
            checkError(pid.setOutputRange(-1.0, 1.0, 1), "PID setting output range {}");
        }
    }

    @Override
    public void disable() {
    }

    @Override
    public void stop() {
        checkError(pid.setReference(0, CANSparkMax.ControlType.kVoltage), "PID setting reference {}");

        setSpeed(0.0);
    }

    @Override
    public void setTurretAngle(double angle) {
        // if (angle >= turretMaxAngle) {
        // angle = turretMaxAngle;
        // } else if (angle <= turretMinAngle) {
        // angle = turretMinAngle;
        // }

        double targetCounts = convertTurretAngleToCounts(angle);

        checkError(pid.setReference(targetCounts, CANSparkMax.ControlType.kPosition, 1), "PID setting reference {}");
    }



    @Override
    public void setAngleFromVision() {
        double heading_error = vision.getError();

        if (Math.abs(heading_error) > 10) {
            motor.setVoltage(0);
            return;
        }

        double Kp = -0.75;
        double proportional_error = Kp * heading_error;

        double Ke = -2.0;
        double delta_error = Ke * (heading_error - heading_last);
        heading_last = heading_error;

        double steering_adjust = proportional_error - delta_error;

        if (steering_adjust > 4) {
            steering_adjust = 4;
        }
        if (steering_adjust < -4) {
            steering_adjust = -4;
        }

        SmartDashboard.putNumber(TelemetryNames.Turret.visionPIDOutput, steering_adjust);

        // checkError(pid.setReference(steering_adjust, CANSparkMax.ControlType.kVoltage, 1), "PID setting reference {}");
        motor.setVoltage(steering_adjust);
    }

    @Override
    public void home() {
        logger.debug("starting ...");
        SmartDashboard.putBoolean(TelemetryNames.Turret.isHomed, false);

        // if (DriverStation.isAutonomous()) {
        // encoder.setPosition(convertTurretAngleToCounts(-90));
        // } else if (DriverStation.isTeleop()) {
        double firstAngle = getAngle();

        /*
         * IMPORTANT - The inner loop get() needs to be there!
         */
        // TODO - Figure out why this doesn't work if no inner get()

        logger.debug("gross test");
        while (!(location.get())) {
            logger.debug("sensor = {}", location.get());
            setSpeed(0.55);
            if (getAngle() - firstAngle >= 100) {
                return;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                logger.warn("interrupted sleep: ", ex);
                // TODO Should this do something?
            }
        }
        setSpeed(0.0);
        logger.debug("found set point (gross)");

        logger.debug("back off");
        while ((location.get())) {
            logger.debug("sensor = {}", location.get());
            setSpeed(-0.05);
            if (getAngle() - firstAngle >= 100) {
                return;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                logger.warn("interrupted sleep: ", ex);
                // TODO Should this do something?
            }
        }
        setSpeed(0.0);
        logger.debug("backed off set point");

        logger.debug("fine test");
        while (!(location.get())) {
            logger.debug("sensor = {}", location.get());
            setSpeed(0.03);
            if (getAngle() - firstAngle >= 100) {
                return;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                logger.warn("interrupted sleep: ", ex);
                // TODO Should this do something?
            }
        }
        setSpeed(0.0);
        logger.debug("found set point (fine)");

        encoder.setPosition(55);

        motor.setIdleMode(IdleMode.kCoast);
        // }

        SmartDashboard.putBoolean(TelemetryNames.Turret.isHomed, true);
        logger.debug("... done");
    }

    private double convertTurretCountsToAngle(double counts) {
        double angle = counts / VPGearing / beltGearing * 360 /* 360 degrees per 1 revolution */;

        return angle;
    }

    private double convertTurretAngleToCounts(double angle) {
        double counts = angle / 360 /* 360 degrees per 1 revolution */ * beltGearing * VPGearing;

        return counts;
    }

    private double getAngle() {
        return convertTurretCountsToAngle(encoder.getPosition());
    }

    @Override
    public void setSpeed(int canID, double speed) {
        speed *= 0.55;
        switch (canID) {
            case 20:
                setSpeed(speed);
                break;
            default:
                break;
        }
    }

    @Override
    public void jogCW() {
        setTurretAngle(getAngle() + 2.5);
    }

    @Override
    public void jogCCW() {
        setTurretAngle(getAngle() - 2.5);
    }

    @Override
    public void holdAngle() {
        double currentAngle = getAngle();

        setTurretAngle(currentAngle);
    }

    @Override
    public boolean isAtAngle(double targetAngle) {
        return getAngle() >= targetAngle;
    }

    private void setSpeed(double speed) {
        setTlmSetSpeed(speed);

        motor.set(speed);
    }

}
