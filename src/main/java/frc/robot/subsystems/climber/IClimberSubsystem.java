/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.climber;

import frc.robot.subsystems.ISubsystem;

/**
 * Add your docs here.
 **/
public interface IClimberSubsystem extends ISubsystem {

    /**
     * Stop the climber from any motion it may have been running under.
     */
    public void stop();

    /**
     * Runs the climber motors at the passed speed.
     * 
     * @param speed
     */
    public void run(double speed);

    /**
     * Use PID control on the elbow to make it go to a set point.
     * 
     * @param setPoint
     */
    public void goToSetPoint(double setPoint);

    /**
     * Returns the value of the climber's left encoder (if present)
     */
    public double getLeftPosition();

    /**
     * Returns the value of the climber's right encoder (if present)
     */
    public double getRightPosition();

    /**
     * Returns the average value of the climber encoder
     */
    public double getAveragePosition();

    /**
     * Zeros the climber's encoder (if present)
     */
    public void zeroPosition();

    /**
     * Rotates the climber (arms) backward to end point.
     */
    public void rotateBackward();

    /**
     * Rotates the climber (arms) forward to end point.
     */
    public void rotateForward();

}
