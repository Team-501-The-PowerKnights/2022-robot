/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.hmi;


import riolog.PKLogger;
import riolog.RioLogger;


abstract class F310Gamepad extends BaseGamepad {
    
    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(F310Gamepad.class.getName());

    protected F310Gamepad(int port)
    {
        super(port);
        logger.info("constructing {}");

        logger.info("constructed");
    }
    
    protected final int leftYAxis = 1;
    protected double getLeftYAxis() {
        return -stick.getRawAxis(leftYAxis);
    }

    protected final int leftXAxis = 0;
    protected double getLeftXAxis() {
        return stick.getRawAxis(leftXAxis);
    }

    protected final int rightYAxis = 5;
    protected double getRightYAxis() {
        return -stick.getRawAxis(rightYAxis);
    }

    protected final int rightXAxis = 4;
    protected double getRightXAxis() {
        return stick.getRawAxis(rightXAxis);
    }

    protected final int leftTrigger = 2;
    protected double getLeftTrigger() {
        return stick.getRawAxis(leftTrigger);
    }

    protected final int rightTrigger = 3;
    protected double getRightTrigger() {
        return stick.getRawAxis(rightTrigger);
    }

    protected final int greenButton = 1;
    protected boolean getGreenButton() {
        return stick.getRawButton(greenButton);
    }

    protected final int redButton = 2;
    protected boolean getRedButton() {
        return stick.getRawButton(redButton);
    }

    protected final int blueButton = 3;
    protected boolean getBlueButton() {
        return stick.getRawButton(blueButton);
    }

    protected final int yellowButton = 4;
    protected boolean getYellowButton() {
        return stick.getRawButton(yellowButton);
    }

    protected final int leftBumper = 5;
    protected boolean getLeftBumper() {
        return stick.getRawButton(leftBumper);
    }

    protected final int rightBumper = 6;
    protected boolean getRightBumper() {
        return stick.getRawButton(rightBumper);
    }

    protected final int backButton = 7;
    protected boolean getBackButton() {
        return stick.getRawButton(backButton);
    }

    protected final int startButton = 8;
    protected boolean getStartButton() {
        return stick.getRawButton(startButton);
    }

    protected final boolean isPov0() {
        return(stick.getPOV() == 0);
    }

    protected final boolean isPov90() {
        return (stick.getPOV() == 90);
    }

    protected final boolean isPov180() {
        return (stick.getPOV() == 180);
    }
    
    protected final boolean isPov270() {
        return (stick.getPOV() == 270);
    }

}
