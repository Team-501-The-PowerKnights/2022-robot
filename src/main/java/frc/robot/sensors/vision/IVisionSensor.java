/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.sensors.vision;

import frc.robot.sensors.ISensor;

/**
 * Add your docs here.
 **/
public interface IVisionSensor extends ISensor {

    /**
     * Whether the vision sensor is active or not?
     * 
     * @return
     **/
    public boolean isActive();

    public double getError();

    public double getY();

    public boolean isLocked();

}
