/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/


package frc.robot.hmi;


import edu.wpi.first.wpilibj.GenericHID;

import riolog.PKLogger;
import riolog.RioLogger;


abstract class BaseGamepad  implements IGamepad {

    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(BaseGamepad.class.getName());

    /** Our joystick */
    protected final GenericHID stick;

    protected BaseGamepad(int port)
    {
        logger.info("constructing {}");

        stick = new GenericHID(port);

        logger.info("constructed");
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
    protected final double deadBand(final double value, final double cutOff) {
        double retValue;
        if (value < cutOff && value > (cutOff * (-1))) {
            retValue = 0;
        } else {
            retValue = (value - (Math.abs(value) / value * cutOff)) / (1 - cutOff);
        }
        return retValue;
    }

    
    protected double getLeftYAxis() {
        return -stick.getRawAxis(1);
    }

    protected double getLeftXAxis() {
        return stick.getRawAxis(0);
    }

    protected double getRightYAxis() {
        return -stick.getRawAxis(5);
    }

    protected double getRightXAxis() {
        return stick.getRawAxis(4);
    }

    protected double getLeftTrigger() {
        return stick.getRawAxis(2);
    }

    protected double getRightTrigger() {
        return stick.getRawAxis(3);
    }

    protected boolean getGreenButton() {
        return stick.getRawButton(1);
    }

    protected boolean getRedButton() {
        return stick.getRawButton(2);
    }

    protected boolean getRightBumper() {
        return stick.getRawButton(6);
    }

    protected boolean getStartButton() {
        return stick.getRawButton(8);
    }

}