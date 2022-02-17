/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.hmi;


import frc.robot.telemetry.ITelemetryProvider;


/**
 * Add your docs here.
 */
public interface IGamepad extends ITelemetryProvider {
    
    /**
     * 
     * //@formatter:off
         * whenPressed() - starts command when newly pressed
         * whileHeld() - starts the command continuously while pressed
         * whenHeld() - starts the command when pressed; cancels when released
         * whenReleased() - starts the command when released
         * toggleWhenPressed()
         * cancelWhenPressed()
        //@formatter:on
     */
    public void configureButtonBindings();

}
