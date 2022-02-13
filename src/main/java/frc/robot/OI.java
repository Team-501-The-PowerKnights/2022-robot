/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot;


import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.Button;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

import frc.robot.commands.InvalidButton;
import frc.robot.commands.drive.DriveSwap;
import frc.robot.telemetry.ITelemetryProvider;
import frc.robot.telemetry.TelemetryNames;

import frc.robot.utils.PKStatus;

import riolog.PKLogger;
import riolog.RioLogger;


/**
 * Add your docs here.
 */
public class OI implements ITelemetryProvider {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(OI.class.getName());

    /** Singleton instance of class for all to use **/
    private static OI ourInstance;
    /** Name of our subsystem **/
    private final static String myName = TelemetryNames.OI.name;

    public static synchronized void constructInstance() {
        SmartDashboard.putNumber(TelemetryNames.OI.status, PKStatus.unknown.tlmValue);

        if (ourInstance != null) {
            throw new IllegalStateException(myName + " already constructed");
        }

        SmartDashboard.putNumber(TelemetryNames.OI.status, PKStatus.inProgress.tlmValue);

        ourInstance = new OI();

        SmartDashboard.putNumber(TelemetryNames.OI.status, PKStatus.success.tlmValue);
    }

    public static OI getInstance() {
        if (ourInstance == null) {
            throw new IllegalStateException(myName + " not constructed yet");
        }
        return ourInstance;
    }

    private final Joystick driverStick;
    private final Button turboButton;
    private final Button crawlButton;
    private final Button driveSwapButton;
    // Only in the pits
    private final Joystick operatorStick;

    private OI() {
        logger.info("constructing {}", myName);

        driverStick = new Joystick(0);
        turboButton = new JoystickButton(driverStick, 5);
        crawlButton = new JoystickButton(driverStick, 6);
        driveSwapButton = new JoystickButton(driverStick, 3);

        operatorStick = new Joystick(1);

        logger.info("constructed");
    }

    public void configureButtonBindings() {
        //@formatter:off
        /*
         * whenPressed() - starts command when newly pressed
         * whileHeld() - starts the command continuously while pressed
         * whenHeld() - starts the command when pressed; cancels when released
         * whenReleased() - starts the command when released
         * toggleWhenPressed()
         * cancelWhenPressed()
         */
        //@formatter:on

        // turboButton - implemented in getting values speed & turn
        // crawlButton - implemented in getting values speed & turn
        driveSwapButton.whenPressed(new DriveSwap());

        /*
         * Reserved
         */
    }

    @Override
    public void updateTelemetry() {
        SmartDashboard.putNumber(TelemetryNames.HMI.rawSpeed, getRawDriveSpeed());
        SmartDashboard.putNumber(TelemetryNames.HMI.rawTurn, getRawDriveTurn());
        SmartDashboard.putBoolean(TelemetryNames.HMI.turbo, turboButton.get());
        SmartDashboard.putBoolean(TelemetryNames.HMI.crawl, crawlButton.get());
        SmartDashboard.putNumber(TelemetryNames.HMI.oiSpeed, getDriveSpeed());
        SmartDashboard.putNumber(TelemetryNames.HMI.oiTurn, getDriveTurn());
    }

    // FIXME - Change for pit testing like field
    public boolean isFieldConnected() {
        // For Field Running
        return DriverStation.isFMSAttached();

        // For Pit Testing (be careful)
        // return true; // FOR pit testing
    }

    /*****************
     * Drive
     *****************/

    public double getRawDriveSpeed() {
        return deadBand(getDriverLeftYAxis(), 0.05);
    }

    public double getRawDriveTurn() {
        return deadBand(getDriverRightXAxis(), 0.05);
    }

    public double getDriveSpeed() {
        double hmiSpeed = getRawDriveSpeed();
        double calcSpeed;
        if (turboButton.get()) {
            calcSpeed = hmiSpeed; // * 0.80 in 2019
        } else if (crawlButton.get()) {
            calcSpeed = hmiSpeed * 0.30;
        } else {
            calcSpeed = hmiSpeed *= 0.50;
        }
        return calcSpeed;
    }

    public double getDriveTurn() {
        final double hmiTurn = getRawDriveTurn();
        double calcTurn;
        if (turboButton.get()) {
            calcTurn = hmiTurn * 0.60; // * 0.50 in 2019
        } else if (crawlButton.get()) {
            calcTurn = hmiTurn * 0.25;
        } else {
            calcTurn = hmiTurn * 0.30;
        }
        return calcTurn;
    }

    /**
     * 
     * Lifted from:
     * https://www.chiefdelphi.com/t/how-do-i-program-a-joystick-deadband/122625
     * 
     * @param value
     * @param cutOff
     * @return
     */
    private final double deadBand(final double value, final double cutOff) {
        double retValue;
        if (value < cutOff && value > (cutOff * (-1))) {
            retValue = 0;
        } else {
            retValue = (value - (Math.abs(value) / value * cutOff)) / (1 - cutOff);
        }
        return retValue;
    }


    //////////////////////////////////////////////////////////////////
    // TODO - Finish cleaning up these
    //////////////////////////////////////////////////////////////////

    private double getDriverLeftYAxis() {
        return -driverStick.getRawAxis(1);
    }

    private double getDriverRightYAxis() {
        return -driverStick.getRawAxis(5);
    }

    private double getDriverLeftXAxis() {
        return driverStick.getRawAxis(0);
    }

    private double getDriverRightXAxis() {
        return driverStick.getRawAxis(4);
    }

    private double getDriverLeftTrigger() {
        return driverStick.getRawAxis(2);
    }

    private double getDriverRightTrigger() {
        return driverStick.getRawAxis(3);
    }

    private double getDriverTriggerAxis() {
        if (driverStick.getRawAxis(2) > 0.05) {
            return driverStick.getRawAxis(2);
        } else if (driverStick.getRawAxis(3) > 0.05) {
            return -driverStick.getRawAxis(3);
        } else {
            return 0;
        }
    }

    private double getOperatorPotentiometer2() {
        return operatorStick.getRawAxis(2);
    }

    private double getOperatorPotentiometer3() {
        return operatorStick.getRawAxis(3);
    }

    // TODO - Get rid of references to these, or add a joystick on port 2 for
    // operator testing

    private double getOperatorLeftYAxis() {
        return -operatorStick.getRawAxis(1);
    }

    private double getOperatorRightYAxis() {
        return -operatorStick.getRawAxis(5);
    }

    private double getOperatorLeftXAxis() {
        return operatorStick.getRawAxis(0);
    }

    private double getOperatorRightXAxis() {
        return operatorStick.getRawAxis(4);
    }

    private double getOperatorTriggerAxis() {
        if (operatorStick.getRawAxis(2) > 0.05) {
            return operatorStick.getRawAxis(2);
        } else if (operatorStick.getRawAxis(3) > 0.05) {
            return -operatorStick.getRawAxis(3);
        } else {
            return 0;
        }
    }

    private double getOperatorLeftTrigger() {
        return operatorStick.getRawAxis(2);
    }

    private double getOperatorRightTrigger() {
        return operatorStick.getRawAxis(3);
    }

}
