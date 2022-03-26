/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.hmi;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.commands.PKParallelCommandGroup;
import frc.robot.commands.climber.ClimberDoSequencing;
import frc.robot.commands.poses.FirePoseNoVision;
import frc.robot.commands.poses.VisionTrackingPose;
import frc.robot.commands.turret.TurretVisionAlign;
import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * This class implements the Operator's gamepad.
 * <p>
 * See <code>control_mode.md</code> for documentation of how configured and
 * used.
 */
public class OperatorGamepad extends F310Gamepad {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(OperatorGamepad.class.getName());

    private final Button firePoseButton;
    private final Button visionTargettingButton;

    private final Button climbSequenceButton;

    public OperatorGamepad() {
        super("OperatorGamepad", 1);
        logger.info("constructing");

        firePoseButton = new JoystickButton(stick, greenButton);
        visionTargettingButton = new JoystickButton(stick, rightBumper);

        climbSequenceButton = new JoystickButton(stick, startButton);

        logger.info("constructed");
    }

    @Override
    public void updateTelemetry() {
        // SmartDashboard.putBoolean(TelemetryNames.HMI.firePose, firePoseButton.get());
        // SmartDashboard.putBoolean(TelemetryNames.HMI.visionTargetting, visionTargettingButton.get());
        // SmartDashboard.putBoolean(TelemetryNames.HMI.revShooter, revShooterButton.get());
        // SmartDashboard.putBoolean(TelemetryNames.HMI.homeTurret, homeTurretButton.get());

        SmartDashboard.putNumber(TelemetryNames.HMI.elevatorSpeed, getElevatorSpeed());
        SmartDashboard.putNumber(TelemetryNames.HMI.turretJog, getTurretJog());
    }

    @Override
    public void autonomousInit() {
        logger.info("initializing auto for {}", this.getClass().getSimpleName());

        // no button or other trigger for autonomous

        logger.info("initialized auto for {}", myName);
    }

    @Override
    public void teleopInit() {
        logger.info("initializing teleop for {}", myName);

        configureTeleopButtonBindings();
        
        logger.info("initialized teleop for {}", myName);
    }

    private void configureTeleopButtonBindings() {
        logger.info("configure");

        visionTargettingButton.whenHeld(new PKParallelCommandGroup(new TurretVisionAlign(),
                new VisionTrackingPose()));
        firePoseButton.whenHeld(new FirePoseNoVision());

        logger.info("configured");
    }

    /**
     * (Re-)Configures the button bindings on the gamepad for the
     * climbing end game play.
     */
    public void configureClimbingButtonBindings() {
        logger.info("configure");

        climbSequenceButton.whileHeld(new ClimberDoSequencing());

        logger.info("configured");
    }

    /*********************
     * Elevator
     *********************/

    public double getElevatorSpeed() {
        // + is forward/up and - is backward/down
        return deadBand(-getLeftYAxis(), 0.05);
    }

    /*********************
     * Turret
     *********************/

    public double getTurretJog() {
        // + is CCW and - is CW
        return deadBand(getTriggerAxis(), 0.05);
    }

    private double getTriggerAxis() {
        if (getLeftTrigger() > getRightTrigger()) {
            return getLeftTrigger();
        } else {
            return -getRightTrigger();
        }
    }

    /*********************
     * Climber
     *********************/

    public boolean getClimberStart() {
        return getStartButton();
    }

    public double getClimberSpeed() {
        return deadBand(getRightYAxis(), 0.05);
    }

}
