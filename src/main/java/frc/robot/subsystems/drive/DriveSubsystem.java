/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.drive;

import java.util.List;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.REVLibError;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveOdometry;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.math.trajectory.constraint.DifferentialDriveVoltageConstraint;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.math.controller.RamseteController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.RamseteCommand;

import frc.robot.sensors.gyro.GyroFactory;
import frc.robot.sensors.gyro.IGyroSensor;
import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;

class DriveSubsystem extends BaseDriveSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(DriveSubsystem.class.getName());

    /**
     * Drive Constants
     */
    private static final double s = 0.12715; // Volts
    private static final double v = 2.1836; // VoltSeconds Per Meter
    private static final double a = 0.44028; // VoltSecondsSquared Per Meter
    private static final double p = 2.3834; // Drive Velocity
    private static final double trackWidth = 0.56411; // Meters
    private static final double ramseteB = 2;
    private static final double ramseteZeta = 0.7;
    private static final double maxSpeed = 1; // Meters Per Second
    private static final double maxAcceleration = 0.5; // Meters Per Second Squared
    private static final boolean leftReversed = false;
    private static final boolean rightReversed = false;
    private static final double wheelRadius = 0.0762; // Meters
    private static final double beltGearing = 1;
    private static final double gearboxGearing = 8.45; // Standard AndyMark KoP chassis Toughbox Mini gearing

    // Voltage constraint for trajectory following
    private final DifferentialDriveVoltageConstraint autoVoltageConstraint;

    // Trajectory configuration for trajectory following
    private final TrajectoryConfig trajectoryConfig;

    /**
     * Mechanisms and Sensors
     */

    private final CANSparkMax leftFrontMotor;
    private final CANSparkMax leftRearMotor;
    private final CANSparkMax rightFrontMotor;
    private final CANSparkMax rightRearMotor;

    private final RelativeEncoder leftEncoder;
    private final RelativeEncoder rightEncoder;

    private final IGyroSensor nav;

    private final DifferentialDrive drive;
    public DifferentialDriveKinematics driveKinematics;
    public DifferentialDriveOdometry driveOdometry;

    private DriveHelper helper;

    DriveSubsystem() {
        logger.info("constructing");

        lastError = REVLibError.kOk;

        // Instantiation and factory default-ing motors
        leftFrontMotor = new CANSparkMax(11, MotorType.kBrushless);
        checkError(leftFrontMotor.restoreFactoryDefaults(), "LF setting factory defaults {}");

        leftRearMotor = new CANSparkMax(12, MotorType.kBrushless);
        checkError(leftRearMotor.restoreFactoryDefaults(), "LR setting factory defaults {}");

        rightFrontMotor = new CANSparkMax(13, MotorType.kBrushless);
        checkError(rightFrontMotor.restoreFactoryDefaults(), "RF setting factory defaults {}");

        rightRearMotor = new CANSparkMax(14, MotorType.kBrushless);
        checkError(rightRearMotor.restoreFactoryDefaults(), "RR setting factory defaults {}");

        // FIXME: Use MotorControllerGroup (see Proto ...)

        rightFrontMotor.setInverted(true);
        // leftFrontMotor.setInverted(true);

        // Following
        checkError(leftRearMotor.follow(leftFrontMotor), "L setting following mode {}");
        checkError(rightRearMotor.follow(rightFrontMotor), "R setting following mode {}");

        // Ramp rates
        checkError(leftFrontMotor.setOpenLoopRampRate(ramp), "L setting ramp rate {}");
        checkError(rightFrontMotor.setOpenLoopRampRate(ramp), "R setting ramp rate {}");

        // Instantiation of encoders and zeroing
        leftEncoder = leftFrontMotor.getEncoder();
        rightEncoder = rightFrontMotor.getEncoder();

        checkError(leftEncoder.setPosition(0.0), "L zeroing the encoder {}");
        checkError(rightEncoder.setPosition(0.0), "R zeroing the encoder {}");

        nav = GyroFactory.getInstance();

        // Trajectory pieces instantiation
        drive = new DifferentialDrive(leftFrontMotor, rightFrontMotor);
        drive.setSafetyEnabled(false);
        driveKinematics = new DifferentialDriveKinematics(trackWidth);
        driveOdometry = new DifferentialDriveOdometry(Rotation2d.fromDegrees(nav.getAngle()));

        autoVoltageConstraint = new DifferentialDriveVoltageConstraint(new SimpleMotorFeedforward(s, v, a),
                driveKinematics, 10);

        trajectoryConfig = new TrajectoryConfig(maxSpeed, maxAcceleration).setKinematics(driveKinematics)
                .addConstraint(autoVoltageConstraint);

        helper = new DriveHelper();

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
    public void periodic() {
        // This method will be called once per scheduler run
        driveOdometry.update(Rotation2d.fromDegrees(nav.getAngle()), leftEncoder.getPosition(),
                rightEncoder.getPosition());
    }

    @Override
    public void updateTelemetry() {
        super.updateTelemetry();

        SmartDashboard.putNumber(TelemetryNames.Drive.encoderClicks, getEncoderClicks());
    }

    @Override
    public void validateCalibration() {
        // Nothing here
    }

    @Override
    public void updatePreferences() {
        super.updatePreferences();

        // TODO: Update the PID values based on preferences

        logger.info("setting OpenLoopRate={}", ramp);
        checkError(leftFrontMotor.setOpenLoopRampRate(ramp), "L setting ramp rate {}");
        checkError(rightFrontMotor.setOpenLoopRampRate(ramp), "R setting ramp rate {}");
    }

    @Override
    public void disable() {
        // TODO Auto-generated method stub
    }

    @Override
    public void setBrake(boolean brakeOn) {
        IdleMode mode = (brakeOn) ? IdleMode.kBrake : IdleMode.kCoast;

        checkError(leftFrontMotor.setIdleMode(mode), "LF setting idle mode {}");
        checkError(leftRearMotor.setIdleMode(mode), "LR setting idle mode {}");
        checkError(rightFrontMotor.setIdleMode(mode), "RF setting idle mode {}");
        checkError(rightRearMotor.setIdleMode(mode), "RR setting idle mode {}");
    }

    @Override
    public void stop() {
        drive.tankDrive(0, 0);
    }

    @Override
    public void swap() {
        // FIXME - Can't just do this without ensuring robot is 'still'
        // FIXME - the API for this changed; needs to be in the follow?
        logger.warn("not implemented yet");
        // leftFrontMotor.setInverted(leftFrontMotor.getInverted() ? false : true);
        // rightFrontMotor.setInverted(rightFrontMotor.getInverted() ? false : true);
    }

    /*
     * Drive constraint values
     */

    private final double quickTurnThreshold = 0.2;

    @Override
    public void drive(double hmiSpeed, double hmiTurn) {
        // Save off passed values for telemetry
        speed = hmiSpeed;
        turn = hmiTurn;

        boolean quickTurn = (Math.abs(speed) < quickTurnThreshold);
        DriveSignal driveSignal = helper.cheesyDrive(speed, turn, quickTurn, false);

        arcadeDrive(driveSignal);
    }

    private void arcadeDrive(DriveSignal driveSignal) {
        // Save values for telemetry
        leftSpeed = driveSignal.getLeft();
        rightSpeed = driveSignal.getRight();

        leftFrontMotor.set(leftSpeed);
        rightFrontMotor.set(rightSpeed);
    }

    @Override
    public void followPath(final Pose2d start, final List<Translation2d> interiorWaypoints, final Pose2d end) {

        // Create trajectory to follow
        final Trajectory trajectory = TrajectoryGenerator.generateTrajectory(start, interiorWaypoints, end,
                trajectoryConfig);

        // return the RamseteCommand to run
        CommandScheduler.getInstance()
                .schedule(new RamseteCommand(trajectory, this::getPose, new RamseteController(ramseteB, ramseteZeta),
                        new SimpleMotorFeedforward(s, v, a), driveKinematics, this::getVelocity,
                        new PIDController(p, 0, 0), new PIDController(p, 0, 0), this::tankDriveVolts, this));
    }

    protected double convertInchesToEncoderClicks(double inches) {
        return inches * (1 / 12) // Conversion to feet
                * (1 / 3.281) // Conversion to meters
                * (1 / (2 * Math.PI * wheelRadius)) // Convert to wheel revolutions (Circumference)
                * (beltGearing) // Convert to output shaft revolutions (Belt gearing)
                * (1 / gearboxGearing); // Convert to motor revolutions (TB Mini gearing)
    }

    /*
     * RAMSETE Methods
     */

    /**
     * 
     * @param leftVolts
     * @param rightVolts
     */
    private void tankDriveVolts(final double leftVolts, final double rightVolts) {
        leftFrontMotor.setVoltage(leftVolts * (leftReversed ? -1 : 1));
        rightFrontMotor.setVoltage(rightVolts * (rightReversed ? -1 : 1));
    }

    /**
     * Returns the currently-estimated pose of the robot.
     *
     * @return The pose.
     */
    private Pose2d getPose() {
        return driveOdometry.getPoseMeters();
    }

    /**
     * Returns the current wheel speeds of the robot.
     *
     * @return The current wheel speeds.
     */
    private DifferentialDriveWheelSpeeds getVelocity() {
        return new DifferentialDriveWheelSpeeds(leftEncoder.getVelocity(), rightEncoder.getVelocity());
    }

    @Override
    public void setSpeed(int canID, double speed) {
        switch (canID) {
            case 11:
                leftFrontMotor.set(speed);
                break;
            case 12:
                leftRearMotor.set(speed);
                break;
            case 13:
                rightFrontMotor.set(speed);
                break;
            case 14:
                rightRearMotor.set(speed);
                break;
            default:
                break;
        }
    }

    @Override
    public double getEncoderClicks() {
        return ((leftEncoder.getPosition() + rightEncoder.getPosition()) / 2);
    }

    @Override
    public double getEncoderVelocity() {
        return ((leftEncoder.getVelocity() + rightEncoder.getVelocity()) / 2);
    }
}
