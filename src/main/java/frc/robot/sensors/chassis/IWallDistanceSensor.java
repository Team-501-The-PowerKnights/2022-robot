/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.sensors.chassis;


import frc.robot.sensors.ISensor;


/**
 * Add your docs here
 */
public interface IWallDistanceSensor extends ISensor {

    /**
     * Returns the distance to the current obstruction (assumed to
     * be the wall) in decimal inches.
     * 
     * @return distance from wall (inches)
     */
    public double get();

    /**
     * Returns an indication of whether the value currently
     * being returned for distance is valid or not.
     * 
     * @return whether distance solution is valid or not
     */
    public boolean isValid();
    
}
