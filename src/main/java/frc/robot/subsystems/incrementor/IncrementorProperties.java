/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.subsystems.incrementor;


import frc.robot.subsystems.SubsystemNames;

import riolog.PKLogger;
import riolog.RioLogger;


public final class IncrementorProperties {

    /** Our classes' logger **/
    @SuppressWarnings("unused")
    private static final PKLogger logger = RioLogger.getLogger(IncrementorProperties.class.getName());

    private IncrementorProperties() {
    }

    static private final String name = SubsystemNames.incrementorName;
    static final String className = name + ".className";
    static final String defaultCommandName = name + ".defaultCommandName";

}
