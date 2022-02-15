/*-----------------------------------------------------------------------*/
/* Copyright (c) Team 501 - The PowerKnights. All Rights Reserved.       */
/* Open Source Software - may be modified and shared by other FRC teams  */
/* under the terms of the Team501 license. The code must be accompanied  */
/* by the Team 501 - The PowerKnights license file in the root directory */
/* of this project.                                                      */
/*-----------------------------------------------------------------------*/

package frc.robot.commands;


import frc.robot.subsystems.SubsystemNames;


/**
 * Add your docs here.
 */
public final class CommandingNames {
    //TODO: Is this actually used anywhere now?

    private static final String cmdPrefix = ".cmd-";

    /***************
     * Power Cells
     ***************/

    public final class Shooter {
        public static final String name = SubsystemNames.shooterName;

        public static final String speed = name + cmdPrefix + "speed";
        public static final String tolerance = name + cmdPrefix + "tolerance";
    }

}
