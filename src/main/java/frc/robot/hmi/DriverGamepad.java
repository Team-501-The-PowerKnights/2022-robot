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

import frc.robot.commands.drive.DriveSwap;
import frc.robot.telemetry.TelemetryNames;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * This class implements the Driver's gamepad.
 * <p>
 * See <code>control_mode.md</code> for documentation of how configured and used.
 */
public class DriverGamepad extends F310Gamepad {
    
    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(DriverGamepad.class.getName());

    private final Button turboButton;
    private final Button crawlButton;
    private final Button driveSwapButton;
    
    public DriverGamepad() 
    {
        super(0);
        logger.info("constructing {}");

        turboButton = new JoystickButton(stick, leftBumper);
        crawlButton = new JoystickButton(stick, rightBumper);
        driveSwapButton = new JoystickButton(stick, backButton);

        logger.info("constructed");
    }

    @Override
    public void configureButtonBindings() {
        logger.info("configure");

        // turboButton - implemented in getting values speed & turn
        // crawlButton - implemented in getting values speed & turn
        driveSwapButton.whenPressed(new DriveSwap());

        // Hook to configure for testing of new stuff
        configureTestBindings();

        logger.info("configured");
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putNumber(TelemetryNames.HMI.rawSpeed, getRawDriveSpeed());
        SmartDashboard.putNumber(TelemetryNames.HMI.rawTurn, getRawDriveTurn());
        SmartDashboard.putBoolean(TelemetryNames.HMI.turbo, turboButton.get());
        SmartDashboard.putBoolean(TelemetryNames.HMI.crawl, crawlButton.get());
        SmartDashboard.putNumber(TelemetryNames.HMI.oiSpeed, getDriveSpeed());
        SmartDashboard.putNumber(TelemetryNames.HMI.oiTurn, getDriveTurn());

        SmartDashboard.putNumber(TelemetryNames.HMI.intakeSpeed, getIntakeSpeed());
    }

    /*********************
     * Drive
     *********************/

    public double getDriveSpeed() {
        double hmiSpeed = getRawDriveSpeed();
        double calcSpeed;
        if (turboButton.get()) {
            calcSpeed = hmiSpeed;
        } else if (crawlButton.get()) {
            calcSpeed = hmiSpeed * 0.30;
        } else {
            calcSpeed = hmiSpeed *= 0.50;
        }
        return calcSpeed;
    }

    private double getRawDriveSpeed() {
        return deadBand(getLeftYAxis(), 0.05);
    }

    public double getDriveTurn() {
        final double hmiTurn = getRawDriveTurn();
        double calcTurn;
        if (turboButton.get()) {
            calcTurn = hmiTurn * 0.60;
        } else if (crawlButton.get()) {
            calcTurn = hmiTurn * 0.25;
        } else {
            calcTurn = hmiTurn * 0.30;
        }
        return calcTurn;
    }

    private double getRawDriveTurn() {
        return deadBand(getRightXAxis(), 0.05);
    }

    /*********************
     * Intake
     *********************/

    public double getIntakeSpeed() {
        return deadBand(getTriggerAxis(), 0.05);
    }
 
    private double getTriggerAxis() {
        if (getLeftTrigger() > getRightTrigger())
        {
            return getLeftTrigger();
        }
        else
        {
            return -getRightTrigger();
        }
    }

    /*********************
     * Climber
     *********************/

    public boolean getClimberStart() {
        return getStartButton();
    }

    /*********************
     * Test Stuff
     *********************/

    // TESTCODE: Button binding for commands here
    private void configureTestBindings() {
        // *** Timer Test
        // Button testButton = new JoystickButton(stick, 3);
        // testButton.whenPressed(new frc.robot.commands.test.TimerTestCommand());
     }

}
