/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.shooter;

import frc.robot.subsystems.ISubsystem;

/**
 * Add your docs here.
 **/
public interface IShooterSubsystem extends ISubsystem {

    /**
     * Stop the shooter from any motion it may have been running under.
     */
    public void stop();

    /**
     * Sets the shooter to a target rpm value.
     * 
     * @param rpm
     */
    public void setTargetRpm(double rpm);

    /**
     * Uses predetermined shooting speeds to shoot.
     */
    public void shoot();

    /**
     * Sets a motor to a speed. Used for troubleshooting.
     * 
     * @param canID
     * @param speed
     */
    public void setSpeed(int canID, double speed);

    /**
     * Method to check if the shooter has reached target velocity.
     * 
     * @return true if the shooter has reached the target velocity
     */
    public boolean atTargetVelocity();

    /**
     * Method to get the active shooter position.
     * 
     * @return the active position
     */
    public String getActivePosition();

}
