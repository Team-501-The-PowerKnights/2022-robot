/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.drive;


import java.util.List;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.motorcontrol.VictorSP;
import frc.robot.sensors.gyro.GyroFactory;
import frc.robot.sensors.gyro.IGyroSensor;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;

import riolog.PKLogger;
import riolog.RioLogger;


class SuitcaseDriveSubsystem extends BaseDriveSubsystem {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(SuitcaseDriveSubsystem.class.getName());

    private final TalonSRX leftFrontMotor;
    private final TalonSRX leftRearMotor;
    private final VictorSP rightFrontMotor;
    private final VictorSP rightRearMotor;

    private final MotorControllerGroup right;

    @SuppressWarnings("unused")
    private final IGyroSensor nav;


    SuitcaseDriveSubsystem() {
        logger.info("constructing");

        leftFrontMotor = new TalonSRX(12);
        leftRearMotor = new TalonSRX(13);
        rightFrontMotor = new VictorSP(1);
        rightRearMotor = new VictorSP(0);

        leftRearMotor.follow(leftFrontMotor);
        right = new MotorControllerGroup(rightFrontMotor, rightRearMotor);

        leftFrontMotor.setInverted(false);
        right.setInverted(true);

        nav = GyroFactory.getInstance();

        logger.info("constructed");
    }

    @Override
    public void periodic() {
        // This method will be called once per scheduler run
    }

    @Override
    public void updateTelemetry() {
        super.updateTelemetry();
        // TODO Auto-generated method stub

    }

    @Override
    public void validateCalibration() {
        // TODO Auto-generated method stub

    }

    @Override
    public void updatePreferences() {
        // TODO Auto-generated method stub

    }

    @Override
    public void disable() {
        // TODO Auto-generated method stub

    }

    @Override
    public void stop() {
        // Nothing is actually moving on the suitcase bot for drive
    }

    @Override
    public void setBrake(boolean brakeOn) {
        // No implementation - Suitcase has no Spark Max on its drive
    }

    @Override
    public void drive(double hmiSpeed, double hmiTurn) {
        // set for telemetry
        speed = hmiSpeed;
        turn = hmiTurn;
        leftSpeed = speed;
        rightSpeed = turn;
        // Really no heavy implementation, just need to see that the controllers can be
        // sent instructions
        leftRearMotor.set(ControlMode.PercentOutput, speed);
        right.set(turn);
    }

    @Override
    public void followPath(Pose2d start, List<Translation2d> interiorWaypoints, Pose2d end) {
        // No implementation - Suitcase has no motors, so testing path following makes
        // no sense
    }

    @Override
    public void setSpeed(int canID, double speed) {
        // TODO Auto-generated method stub

    }

    @Override
    public void swap() {
        // TODO Auto-generated method stub

    }

    @Override
    public double getEncoderClicks() {
        // TODO Auto-generated method stub
        return 0;
    }

}
