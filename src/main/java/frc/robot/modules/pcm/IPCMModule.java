/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.modules.pcm;


import frc.robot.modules.IModule;


public interface IPCMModule extends IModule {

    public void enable();

    public void extendIntake();

    public void retractIntake();

    public void extendClimber();

    public void retractClimber();

}
