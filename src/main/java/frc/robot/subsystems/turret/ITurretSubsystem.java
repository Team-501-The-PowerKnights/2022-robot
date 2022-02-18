/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.turret;

import frc.robot.subsystems.ISubsystem;

/**
 * Add your docs here.
 **/
public interface ITurretSubsystem extends ISubsystem {

    /**
     * Stop the turret from any motion it may have been running under.
     */
    public void stop();

    public void setTurretAngle(double angle);

    public void home();

    public void setSpeed(int canID, double speed);

    public void jogCW();

    public void jogCCW();

    public void setAngleFromVision();

    public void holdAngle();

    public boolean isAtAngle(double targetAngle);

}
