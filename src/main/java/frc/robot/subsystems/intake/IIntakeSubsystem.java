/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.intake;

import frc.robot.subsystems.ISubsystem;

/**
 * Add your docs here.
 **/
public interface IIntakeSubsystem extends ISubsystem {

    /**
     * Stop the intake from any motion it may have been running under.
     */
    public void stop();

    /**
     * Pulls in at the defined "right speed."
     */
    public void pullIn();

    /**
     * Pulls in at a manual speed.
     * 
     * @param speed
     */
    public void pullIn(double speed);

    /**
     * Pushes out at the defined "right speed."
     */
    public void pushOut();

    /**
     * Pushes out at a manually defined speed.
     * 
     * @param speed
     */
    public void pushOut(double speed);

}
