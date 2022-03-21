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
     * Runs the elbow motor at the passed speed.
     * 
     * @param speed
     */
    public void runElbow(double speed);

    /**
     * Runs the shoulder at the passed speed.
     * 
     * @param speed
     */
    public void runShoulder(double speed);

    /**
     * Use PID control on the elbow to make it go to a set point.
     * 
     * @param setPoint
     */
    public void elbowGoToSetPoint(double setPoint);

    /**
     * Use PID control on the shoulder to make it go to a set point.
     * 
     * @param setPoint
     */
    public void shoulderGoToSetPoint(double setPoint);

    /**
     * Returns the value of the climber's encoder (if present)
     */
    public double getShoulderPosition();

    /**
     * Zeros the climber's encoder (if present)
     */
    public void zeroShoulderPosition();

    /**
     * Returns the value of the climber's encoder (if present)
     */
    public double getElbowPosition();

    /**
     * Zeros the climber's encoder (if present)
     */
    public void zeroElbowPosition();

}
