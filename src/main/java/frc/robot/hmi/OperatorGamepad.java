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
import frc.robot.commands.FirePoseVision;
import frc.robot.commands.PKParallelCommandGroup;
import frc.robot.commands.shooter.ShooterSpinUpFormula;
import frc.robot.commands.turret.TurretHome;
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
    // private final Button revShooterButton;
    private final Button homeTurretButton;

    public OperatorGamepad() {
        super(1);
        logger.info("constructing {}");

        firePoseButton = new JoystickButton(stick, greenButton);
        // visionTargettingButton = new JoystickButton(stick, rightBumper);
        visionTargettingButton = new JoystickButton(stick, greenButton);
        // revShooterButton = new JoystickButton(stick, redButton);
        homeTurretButton = new JoystickButton(stick, startButton);

        logger.info("constructed");
    }

    @Override
    public void configureButtonBindings() {
        logger.info("configure");

        // visionTargettingButton
        // .whenHeld(new PKParallelCommandGroup(new TurretVisionAlign(), new
        // ShooterSpinUpFormula()));
        visionTargettingButton
                .whenHeld(new TurretVisionAlign());
        homeTurretButton.whenHeld(new TurretHome());
        // firePoseButton.whenHeld(new FirePoseVision());
        firePoseButton.whenHeld(new PKParallelCommandGroup(new TurretVisionAlign(), new FirePoseVision()));

        logger.info("configured");
    }

    @Override
    public void updateTelemetry() {
        // SmartDashboard.putBoolean(TelemetryNames.HMI.firePose, firePoseButton.get());
        // SmartDashboard.putBoolean(TelemetryNames.HMI.visionTargetting,
        // visionTargettingButton.get());
        // SmartDashboard.putBoolean(TelemetryNames.HMI.revShooter,
        // revShooterButton.get());
        SmartDashboard.putBoolean(TelemetryNames.HMI.homeTurret, homeTurretButton.get());
        SmartDashboard.putNumber(TelemetryNames.HMI.elevatorSpeed, getElevatorSpeed());
        SmartDashboard.putNumber(TelemetryNames.HMI.turretJog, getTurretJog());
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

}
