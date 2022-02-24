/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.shooter;


import riolog.PKLogger;
import riolog.RioLogger;


class ProtoShooterSubsystem extends StubShooterSubsystem {
    
    /** Our classes' logger **/
    private static final PKLogger logger = RioLogger.getLogger(ProtoShooterSubsystem.class.getName());

    ProtoShooterSubsystem() {
        logger.info("constructing");

        logger.info("constructed");
    }

}
