/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.elevator;

import frc.robot.subsystems.ISubsystem;

/**
 * Add your docs here.
 **/
public interface IElevatorSubsystem extends ISubsystem {

    /**
     * Stop the elevator from any motion it may have been running under.
     */
    public void stop();

    public void lift();

    public void lower();

    public void increment();

    public void stopIncrement();

    public boolean isFull();

    public void liftToLimit();

}
